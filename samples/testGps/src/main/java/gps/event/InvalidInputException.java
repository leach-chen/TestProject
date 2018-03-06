package gps.event;

import java.io.IOException;

/*
 * Copyright 2012 Edmundo Carmona Antoranz
 * All rights reserved
 * Released under the terms of Mozilla Public License 2.0
 */

/**
 * Error processing information from GPS device or log
 * 
 * @author antoranz
 * 
 */
public class InvalidInputException extends IOException {
	
	public InvalidInputException() {}
	
	public InvalidInputException(Exception e) {
		super(e);
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
	
	public InvalidInputException(String message, Exception e) {
		super(message, e);
	}

}
 