package gps.event.records;


/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */
public class GPGSA extends GpsInfoRecord {


	public static final int MODE_AUTOMATIC = 1;
	public static final int MODE_MANUAL = 2;
	public static final int MODE_UNKNOWN = -1;

	public static final int FIX_UNAVAILABLE = 1;
	public static final int FIX_2D = 2;
	public static final int FIX_3D = 3;
	public static final int FIX_UNKNOWN = -1;

	private int mode;
	private int fixMode;

	protected GPGSA(String[] fields) {
		super(fields);

		// mode
		if (fields[1].equals("A")) {
			this.mode = MODE_AUTOMATIC;
		} else if (fields[1].equals("M")) {
			this.mode = MODE_MANUAL;
		} else {
			this.mode = MODE_UNKNOWN;
		}

		if (fields[2].equals("1")) {
			this.fixMode = FIX_UNAVAILABLE;
		} else if (fields[2].equals("2")) {
			this.fixMode = FIX_2D;
		} else if (fields[2].equals("3")) {
			this.fixMode = FIX_3D;
		} else {
			this.fixMode = FIX_UNKNOWN;
		}
	}

	public String toString() {
		StringBuffer temp = new StringBuffer("GPGSA mode: ");
		switch (this.mode) {
		case MODE_AUTOMATIC:
			temp.append("Automatic");
			break;
		case MODE_MANUAL:
			temp.append("Manual");
			break;
		default:
			temp.append("Unknown");
		}
		temp.append(" (" + fields[1] + ") Fix Mode: ");
		switch (this.fixMode) {
		case FIX_2D:
			temp.append("2D");
			break;
		case FIX_3D:
			temp.append("3D");
			break;
		case FIX_UNAVAILABLE:
			temp.append("Unavailable");
			break;
		default:
			temp.append("Unknown");
		}
		temp.append(" (" + fields[2] + ")");

		return temp.toString();
	}

	public int getMode() {
		return this.mode;
	}

	public int getFixMode() {
		return this.fixMode;
	}
}
