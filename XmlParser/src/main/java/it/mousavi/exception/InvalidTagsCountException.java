package it.mousavi.exception;

public class InvalidTagsCountException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidTagsCountException(String errorMessage) {
		super(errorMessage);
	}
}
