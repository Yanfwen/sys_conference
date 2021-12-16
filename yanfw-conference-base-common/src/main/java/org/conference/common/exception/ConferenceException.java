package org.conference.common.exception;

public class ConferenceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConferenceException(String message){
		super(message);
	}

	public ConferenceException(Throwable cause)
	{
		super(cause);
	}

	public ConferenceException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
