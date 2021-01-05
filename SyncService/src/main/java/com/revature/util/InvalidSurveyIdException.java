  
package com.revature.util;

@SuppressWarnings("serial")
public class InvalidSurveyIdException extends RuntimeException {
	
	public InvalidSurveyIdException(String msg) {
		super(msg);
	}

}