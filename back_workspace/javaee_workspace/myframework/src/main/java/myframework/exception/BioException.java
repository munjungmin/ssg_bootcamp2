package myframework.exception;

public class BioException extends RuntimeException{

	public BioException(String message, Throwable cause) {
		super(message, cause);
	}

	public BioException(String message) {
		super(message);
	}

	public BioException(Throwable cause) {
		super(cause);
	}

}
