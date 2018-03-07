package gps;

/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */
import gps.event.EndOfStream;
import gps.event.GpsEvent;
import gps.event.GpsEventListener;
import gps.event.InvalidInputException;
import gps.event.records.GPGSV;
import gps.event.records.GPRMC;
import gps.event.records.GpsInfoRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class GpsAnalyzer implements GpsEventListener {


	private final BufferedReader input;

	private HashMap<Integer, Satellite> satellites = new HashMap<Integer, Satellite>();

	/**
	 * Used when refreshing information about satellites to be able to tell
	 * which are out of sight
	 */
	private ArrayList<Satellite> satellitesInView = new ArrayList<Satellite>();

	private LatLongReading lastValidReading;
	private boolean gettingValidReadings = false;
	private GpsThread gpsThread;

	private HashMap<Class<?>, ArrayList<GpsEventListener>> listeners = new HashMap<Class<?>, ArrayList<GpsEventListener>>();

	public GpsAnalyzer(InputStream theInput) {
		this(new InputStreamReader(theInput));
	}

	public GpsAnalyzer(InputStreamReader theInput) {
		this(new BufferedReader(theInput));
	}

	public GpsAnalyzer(BufferedReader theInput) {
		this.input = theInput;
		this.gpsThread = new GpsThread(this, input);
		this.addGpsEventListener(GPGSV.class, this);
		this.addGpsEventListener(GPRMC.class, this);
	}

	public void startAnalyzing() {
		if (!this.gpsThread.isAlive()) {
			this.gpsThread.start();
		}
	}

	/**
	 * We work as a GpsEventListener so we can process only those listeners we
	 * care about
	 */
	public void eventFound(GpsEvent event) {
		if (event instanceof GPGSV) {
			GPGSV gpgsv = (GPGSV) event;
			if (gpgsv.getThisLine() == 1) {
				// it's the first line, let's clear/update satellitesInView
				satellitesInView.clear();
			}

			Satellite satellite = null;
			Iterator<Satellite> sats = gpgsv.getSatellites();
			while (sats.hasNext()) {
				satellite = sats.next();
				satellites.put(satellite.getPRN(), satellite);
				satellitesInView.add(satellite);
			}

			/*
			 * if it's the very last line, let's set satellites not in sight to
			 * (not in sight)
			 */
			if (gpgsv.getThisLine() == gpgsv.getTotalLines()) {
				/*
				 * satellites that are set as "inview" but are not in
				 * satellitesInView will be set as not in view
				 */
				sats = satellites.values().iterator();
				while (sats.hasNext()) {
					satellite = sats.next();
					if (satellite.isInView()) {
						// is it really in view?
						if (!satellitesInView.contains(satellite)) {
							// nope, it's not
							satellite.setNotInView();
						}
					}
				}
				// we are through
				satellitesInView.clear();
			}
		} else if (event instanceof GPRMC) {
			GPRMC gprmc = (GPRMC) event;
			gettingValidReadings = gprmc.isValid();
			if (gettingValidReadings) {
				lastValidReading = new LatLongReading(gprmc.getLatitude(),
						gprmc.getLongitude(), gprmc.getReadingDate());
			}
		}
	}

	private void processInputLine(String inputLine)
			throws InvalidInputException {
		// now we start telling about it to the listeners (including ourselves)
		notifyEvent(GpsInfoRecord.createRecord(inputLine));
	}

	/**
	 * Notify registered listeners about the newly created event
	 * 
	 * @param event
	 */
	private void notifyEvent(GpsEvent event) {
		// first, direct listeners
		ArrayList<GpsEventListener> directListeners = this.listeners.get(event
				.getClass());
		if (directListeners != null) {
			Iterator<GpsEventListener> iter = directListeners.iterator();
			while (iter.hasNext()) {
				iter.next().eventFound(event);
			}
		}

		// now listeners for all kinds of events
		/*
		 * create a new instance so that we don't modify the listeners list when
		 * we remove the duplicates
		 */
		ArrayList<GpsEventListener> listeners = this.listeners
				.get(GpsEvent.class);
		if (listeners != null) {
			// have to work on the clone
			listeners = new ArrayList<GpsEventListener>(listeners);

			// remove duplicates
			if (directListeners != null) {
				listeners.removeAll(directListeners);
			}

			if (listeners != null) {
				Iterator<GpsEventListener> iter = listeners.iterator();
				while (iter.hasNext()) {
					iter.next().eventFound(event);
				}
			}
		}
	}

	/**
	 * Last reading that was reported as valid from GPS device (from RMC)
	 * 
	 * @return last valid reading
	 */
	public LatLongReading getLastValidReading() {
		return this.lastValidReading;
	}

	/**
	 * Indicate if we are getting valid readings at the moment (from RMC)
	 * 
	 * @return
	 */
	public boolean gettingValidReadings() {
		return this.gettingValidReadings;
	}

	public boolean isFinished() {
		return !this.gpsThread.isReading();
	}

	/**
	 * Register a listener to listen to events of a certain kind
	 * 
	 * @param abstractEventClass
	 *            If abstractEventClass is null, it means it will listen to
	 *            every event. If a class is used, it has to be a class that
	 *            extends {@link GpsEvent}
	 * @param listener
	 *            Listener that will be reported about the event
	 */
	public void addGpsEventListener(Class<?> eventClass,
			GpsEventListener listener) throws ClassCastException {
		if (!GpsEvent.class.isAssignableFrom(eventClass)) {
			throw new ClassCastException(eventClass.getName() + " is not a "
					+ GpsEvent.class.getName());
		}
		// it's all right.... let's save the listener
		ArrayList<GpsEventListener> listeners = this.listeners.get(eventClass);
		if (listeners == null) {
			listeners = new ArrayList<GpsEventListener>();
			this.listeners.put(eventClass, listeners);
		}
		// let's not duplicate
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	private static class GpsThread extends Thread {

		private GpsAnalyzer analyzer;
		private BufferedReader reader;
		private boolean reading;

		public GpsThread(GpsAnalyzer analyzer, BufferedReader reader) {
			this.analyzer = analyzer;
			this.reader = reader;
			this.reading = true;
		}

		public void run() {
			String inputLine = null;
			while (reading) {
				try {
					inputLine = reader.readLine();
					if (inputLine == null) {
						analyzer.notifyEvent(new EndOfStream());
						reading = false;
						break;
					}
					analyzer.processInputLine(inputLine);
				} catch (IOException e) {
				}
			}
		}

		public boolean isReading() {
			return reading;
		}
	}

}
