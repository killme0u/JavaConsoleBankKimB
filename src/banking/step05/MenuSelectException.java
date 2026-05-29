package banking.step05;

import java.util.Arrays;

public class MenuSelectException extends CustomException {

	private static final long serialVersionUID = -8568838681229905448L;

	private final String inputValue;

	private String[] validCodes;

	// 숫자가 아닌 문자를 입력했을 때
	public MenuSelectException(String menuNo) {
		super(ErrorCode.NOT_CHAR);
		this.inputValue = menuNo;
		// Null Object Pattern
		// null 대신 비어있는 기본 객체를 반환하여 호출부에서 null 체크없이
		// 사용할 수 있게 하는 방어적 설계
		// menuSelectException.getValidCodes()를 null 체크없이 사용가능
		this.validCodes = new String[0];
	}

	// 존재하지 않는 메뉴 번호를 입력했을 때
	// validCodes: MenuOption.validCodes() 로 전달
	public MenuSelectException(String inputValue, String[] validCodes) {
		super(ErrorCode.NOT_SPECIFIC_DIGIT);
		this.inputValue = inputValue;
		this.validCodes = validCodes;
	}

	public String getInputValue() {
		return inputValue;
	}

	public String[] getValidCodes() {
		return validCodes;
	}

	public boolean isInvalidMenu() {
		return getErrorCode() == ErrorCode.NOT_SPECIFIC_DIGIT;
	}

	@Override
	public String getMessage() {
		if (isInvalidMenu()) {
			return String.format(
				"%s (입력값: '%s', 허용 메뉴: %s)",
				super.getMessage(), inputValue, Arrays.toString(validCodes));
		}
		return String.format("%s (입력값: '%s')", super.getMessage(),
			inputValue == null ? "(없음)" : inputValue);
	}


}
