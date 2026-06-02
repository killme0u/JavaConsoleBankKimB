package banking.step06;

public class CustomException extends Exception {

	private static final long serialVersionUID = 2701394189287524097L;

	private ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
	    return errorCode;
	}
}
