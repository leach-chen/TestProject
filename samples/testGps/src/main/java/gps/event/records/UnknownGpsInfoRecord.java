package gps.event.records;

/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */

/**
 * Unknown record
 * 
 * @author antoranz
 * 
 */
public final class UnknownGpsInfoRecord extends GpsInfoRecord {

	protected UnknownGpsInfoRecord(String[] fields) {
		super(fields);
	}

	public String toString() {
		return "Unknown Event (" + fields[0] + ")";
	}

}
