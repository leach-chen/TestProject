package gps.event;

/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */

/**
 * An event happened while analyzing. Normally it's information coming from GPS
 * device but could be other things like {@link EndOfStream}
 * 
 */
public abstract class GpsEvent {

	protected GpsEvent() {
	}

}
