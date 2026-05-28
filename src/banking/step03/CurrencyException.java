package banking.step03;

public class CurrencyException extends CustomException {

	private static final long serialVersionUID = -3971865473705715348L;

	private final String currency;

	// 금액에 입력값이 다음과 같을 때 예외
	// - 숫자와 comma 이외의 값을 입력할 경우
	// - 금액 형식(1,050,000)이 유효하지 않는 값을 입력할 경우
	public CurrencyException(String currency, ErrorCode errorCode) {
		super(errorCode);
		this.currency = currency;
	}

}
