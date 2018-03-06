package gps.event.records;

import gps.event.GpsEvent;
import gps.event.InvalidInputException;

/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */

/**
 * GPS info record
 */
public class GpsInfoRecord extends GpsEvent {

	protected String[] fields;

	public static GpsInfoRecord createRecord(String inputLine)
			throws InvalidInputException {
		if (inputLine.length() == 0) {
			throw new InvalidInputException("Empty input line");

		}
		if (inputLine.charAt(0) != '$') {
			throw new InvalidInputException("Record doesn't start with $");
		}
		int checksumPos = inputLine.indexOf('*');
		if (checksumPos != -1) {
			/*
			 * FIXME has checksum, let's check it.... but, for now, let's just
			 * strip it
			 */
			inputLine = inputLine.substring(0, checksumPos);
		}

		String[] fields = inputLine.substring(1).split(",");
		if (fields[0].equals("GPGGA")) {
			return new GPGGA(fields);
		} else if (fields[0].equals("GPGLL")) {
			return new GPGLL(fields);
		} else if (fields[0].equals("GPGSA")) {
			return new GPGSA(fields);
		} else if (fields[0].equals("GPGSV")) {
			return new GPGSV(fields, inputLine.endsWith(","));
		} else if (fields[0].equals("GPVTG")) {
			return new GPVTG(fields);
		} else if (fields[0].equals("GPRMC")) {
			return new GPRMC(fields);
		} else {
			return new UnknownGpsInfoRecord(fields);
		}
	}

	public GpsInfoRecord(String[] fields) {
		this.fields = fields;
	}

	/**
	 * Number of fields that make up the GPS event
	 * 
	 * @return
	 */
	public int getFieldsCount() {
		return fields.length;
	}

	public String getField(int index) {
		return fields[index];
	}

	public String toString() {
		return fields[0] + " (from GpsEvent)";
	}

}
