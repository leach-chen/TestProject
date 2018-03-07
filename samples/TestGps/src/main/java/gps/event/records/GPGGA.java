package gps.event.records;

import java.sql.Time;


/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */

public class GPGGA extends GpsInfoRecord {


	public static final int QUALITY_INVALID = 0;
	/**
	 * GPS FIX (SPS)
	 */
	public static final int QUALITY_GPS = 1;
	public static final int QUALITY_DGPS = 2;
	public static final int QUALITY_PPS = 3;
	/**
	 * Realtime Kinematic
	 */
	public static final int QUALITY_RT_KINEMATIC = 4;
	public static final int QUALITY_FLOAT_RTK = 5;
	/**
	 * estimated (dead reckoning) (2.3 feature)
	 */
	public static final int QUALITY_ESTIMATED = 6;
	public static final int QUALITY_MANUAL_INPUT_MODE = 7;
	public static final int QUALITY_SIMULATION_MODE = 8;
	/**
	 * No quality was specified
	 */
	public static final int QUALITY_EMPTY = -1;

	private Time fixTime;
	private int quality;
	private double latitude;
	private double longitude;
	private int satellitesTracked;

	protected GPGGA(String[] fields) {
		super(fields);
		this.fixTime = new Time(Integer.parseInt(fields[1].substring(0, 2)),
				Integer.parseInt(fields[1].substring(2, 4)),
				Integer.parseInt(fields[1].substring(4, 6)));
		if (fields[6].length() == 0) {
			this.quality = QUALITY_EMPTY;
		} else {
			this.quality = Integer.parseInt(fields[6]);
		}
		if (fields[2].length() > 0 && fields[3].length() > 0
				&& fields[4].length() > 0 && fields[5].length() > 0) {
			// got gps position... let.s parse it
			latitude = Integer.parseInt(fields[2].substring(0, 2))
					+ Double.parseDouble(fields[2].substring(2)) / 60;
			if (fields[3].equals("S")) {
				latitude *= -1;
			}
			longitude = Integer.parseInt(fields[4].substring(0, 3))
					+ Double.parseDouble(fields[4].substring(3)) / 60;
			if (fields[5].equals("W")) {
				longitude *= -1;
			}
		} else {
			latitude = -1000;
			longitude = -1000;
		}
		this.satellitesTracked = Integer.parseInt(fields[7]);
	}

	public String toString() {
		StringBuffer temp = new StringBuffer("GGA Fix Time: " + fixTime
				+ " quality: ");
		switch (this.quality) {
		case QUALITY_DGPS:
			temp.append("DGPS");
			break;
		case QUALITY_EMPTY:
			temp.append("EMPTY");
			break;
		case QUALITY_ESTIMATED:
			temp.append("ESTIMATED");
			break;
		case QUALITY_FLOAT_RTK:
			temp.append("Float RTK");
			break;
		case QUALITY_GPS:
			temp.append("GPS");
			break;
		case QUALITY_INVALID:
			temp.append("INVALID");
			break;
		case QUALITY_MANUAL_INPUT_MODE:
			temp.append("Manual Input Mode");
			break;
		case QUALITY_PPS:
			temp.append("PPS");
			break;
		case QUALITY_RT_KINEMATIC:
			temp.append("RT Kinematic");
			break;
		case QUALITY_SIMULATION_MODE:
			temp.append("Simulation Mode");
			break;
		default:
			temp.append("Unknown");
		}
		temp.append(" (" + this.fields[6] + ")");
		if (latitude != -1000 && longitude != -1000) {
			temp.append(" Lat: " + Math.abs(latitude)
					+ (latitude >= 0 ? 'N' : 'S') + " Long: "
					+ Math.abs(longitude) + (longitude >= 0 ? 'E' : 'W'));
		}

		return temp.toString();
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getQuality() {
		return this.quality;
	}

	public int getSatellitesTracked() {
		return this.satellitesTracked;
	}

}
