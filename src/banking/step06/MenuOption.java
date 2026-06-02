package banking.step06;

public enum MenuOption {
	MAKE("1", "계좌 개설"),
	DEPOSIT("2", "입 금"),
	WITHDRAW("3", "출 금"),
	INQUIRE("4", "계좌 정보 출력"),
	DELETE("5", "계좌 정보 삭제"),
	EXIT("6", "프로그램 종료"),
	;

	private final String code;
	private final String label;

	MenuOption(String code, String label) {
		this.code = code;
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	// 입력 값으로 MenuOption 탐색하고 없으면 null 반환
	public static MenuOption from(String input) {
		for (MenuOption option : values()) {
			if (option.code.equals(input)) {
				return option;
			}
		}
		return null;
	}

	// 유효한 메뉴 코드 목록 반환
	public static String[] validCodes() {
		MenuOption[] options = values();
		String[] codes = new String[options.length];

		for (int i = 0; i < codes.length; i++) {
			codes[i] = options[i].code;
		}

		return codes;
	}
}
