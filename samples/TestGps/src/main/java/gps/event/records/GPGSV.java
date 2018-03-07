package gps.event.records;

import java.util.ArrayList;
import java.util.Iterator;

import gps.Satellite;

/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */

/**
 * Satellites in view
 * 
 */
public class GPGSV extends GpsInfoRecord {


	private int thisLine;
	private int totalLines;
	private int satellitesInView;
	private ArrayList<Satellite> satellites = new ArrayList<Satellite>();

	protected GPGSV(String[] fields, boolean endsWithComa) {
		super(fields);

		this.thisLine = Integer.parseInt(fields[2]);
		this.totalLines = Integer.parseInt(fields[1]);


		this.satellitesInView = Integer.parseInt(fields[3]);
		// how many satellites are in the line?
		int numberOfSatellitesInLine = (fields.length + (endsWithComa ? 1 : 0) - 4) / 4;

		Satellite satellite = null;
		for (int i = 0; i < numberOfSatellitesInLine; i++) {
			int prn = -1;
			int elevation = -1;
			int azimuth = -1;
			int snr = -1; // no signal
			satellite = null;
			int fieldIndex = 3 + (i * 4) + 1;
			prn = Integer.parseInt(fields[fieldIndex++]);
			elevation = fields[fieldIndex].length() == 0 ? -1 : Integer
					.parseInt(fields[fieldIndex]);
			fieldIndex++;
			azimuth = fields[fieldIndex].length() == 0 ? -1 : Integer
					.parseInt(fields[fieldIndex]);
			fieldIndex++;
			if (fieldIndex >= fields.length) {
				// probably the line ends up in ,
				snr = -1;
			} else {
				snr = fields[fieldIndex].length() == 0 ? -1 : Integer
						.parseInt(fields[fieldIndex]);
			}

			/*
			 * satellite is obviously <b>in view</b>, right? if it's a new
			 * satellite, let's put it in the satellite map right away
			 */
			satellite = new Satellite(prn, elevation, azimuth, snr, true);

			satellites.add(satellite);
		}

	}

	public int getTotalLines() {
		return this.totalLines;
	}

	public int getThisLine() {
		return this.thisLine;
	}

	/**
	 * Number of satellites that are supposed to be included in this record
	 * according to GPGSV record (GPGSV's 4th field)
	 * 
	 * @return
	 */
	public int getSatellitesinViewCount() {
		return this.satellitesInView;
	}

	public Iterator<Satellite> getSatellites() {
		return this.satellites.iterator();
	}

	public String toString() {
		return fields[0] + " line " + this.thisLine + " of " + this.totalLines
				+ ". Satellites in view (Total): " + this.satellitesInView;
	}

}
