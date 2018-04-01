package gps.event.records;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */

/**
 * Recommended minimum
 *
 */
public class GPRMC extends GpsInfoRecord {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy HHmmss.SSS");
	static {
		// set the timezone for analysis of dates on UTC
		dateFormat.setCalendar(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
	}

	private boolean isValid;
	private double latitude;
	private double longitude;
	private double angelDegrees;
	private Date readingDate;

	protected GPRMC(String[] fields) {
		super(fields);

		try
		{
			if (fields.length >= 10) {
				boolean isDateRight = false;
				try {
					if(fields[9].equals("") || fields[1].equals(""))
					{
						isDateRight = false;
					}else
					{
						isDateRight = true;
						this.readingDate = dateFormat.parse(fields[9] + " " + fields[1]);
					}
				} catch (ParseException e) {
					this.readingDate = new Date();
				}
				if(isDateRight && (fields[2].equals("A") && ((fields[4].equals("S") || fields[4].equals("N")) && (fields[6].equals("W") || fields[6].equals("E")))))
				{
					this.isValid = true;

					if((fields[3].length() == 9 || fields[3].length() == 10) && (fields[5].length() == 10 || fields[5].length() == 11))
					{
						this.isValid = true;
					}else
					{
						this.isValid = false;
					}

					String aSpeed = fields[7];
					if(aSpeed.length() > 0)
					{
						double speed = Double.parseDouble(aSpeed);
						if(speed < 0.7)
						{
							this.isValid = false;
						}
					}

				}else
				{
					this.isValid = false;
				}

				if (this.isValid) {
					String rawLat = fields[3];
					String rawLong = fields[5];
					String aDegrees = fields[8];
					if (rawLat.length() > 0 && rawLong.length() > 0) {
						// we have a winner
						int degrees = Integer.parseInt(rawLat.substring(0, 2));
						double minutes = Double.parseDouble(rawLat.substring(2));
						this.latitude = degrees + minutes / 60;
						if (fields[4].equals("S")) {
							this.latitude *= -1;
						}
						degrees = Integer.parseInt(rawLong.substring(0, 3));
						minutes = Double.parseDouble(rawLong.substring(3));
						this.longitude = degrees + minutes / 60;
						if (fields[6].equals("W")) {
							this.longitude *= -1;
						}

						if (aDegrees.length() > 0) {
							angelDegrees = Double.parseDouble(aDegrees);
						}
					}

				}
			}
		}catch(Exception e)
		{

		}
	}

	public boolean isValid() {
		return isValid;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getAngelDegrees() {
		return angelDegrees;
	}

	public Date getReadingDate() {
		return readingDate;
	}

	public String toString() {
		return fields[0] + " " + (isValid ? "Valid" : "Not Valid") + " at " + readingDate
				+ (this.isValid ? (" Lat: " + Math.abs(latitude) + (latitude < 0 ? "S" : "N") + " Long: "
				+ Math.abs(longitude) + (longitude < 0 ? "W" : "E")) : "");
	}

}
