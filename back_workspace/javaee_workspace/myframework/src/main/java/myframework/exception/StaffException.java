package myframework.exception;

public class StaffException extends RuntimeException{

	public StaffException(String message, Throwable cause) {
		super(message, cause);
	}

	public StaffException(String message) {
		super(message);
	}

	public StaffException(Throwable cause) {
		super(cause);
	}

}
