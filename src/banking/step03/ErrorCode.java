package banking.step03;

public enum ErrorCode {
	SUCCESS("에러 없습니다."),
	NOT_CHAR("문자는 입력할 수 없습니다."),
	NOT_SPECIFIC_DIGIT("지정한 정수 이외의 숫자를 입력할 수 없습니다."),
	NOT_CURRENCY_CHAR("금액 입력 시 숫자와 콤마만 입력할 수 없습니다."),
	NOT_DEPOSIT_AMOUNT("입금액은 500원 단위로 가능합니다. Ex) 1000, 1500원 입금가능, 1600원 입금불가"),
	INSUFFICIENT_BALANCE("잔고가 부족합니다."),
	NOT_VALID_DEPOSIT_UNIT("입금액은 500원 단위로 가능합니다."),
	NOT_VALID_WITHDRAW_UNIT("출금액은 1000원 단위로 가능합니다."),
	NOT_MINUS_DEPOSIT("음수를 입금할 수 없습니다."),
	NOT_MINUS_WITHDRAW("음수를 출금할 수 없습니다.");

	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}