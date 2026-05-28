package banking.step03;

public enum AccountType {
	NORMAL("1", "일반 계좌"),
	HIGH_GREDIT("2", "신용 우대 계좌"),
	;

	private final String code;
	private final String label;

	AccountType(String code, String label) {
		this.code = code;
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static AccountType from(String input) {
		for (AccountType type : values()) {
			if (type.code.equals(input)) {
				return type;
			}
		}
		return null;
	}

	public static String[] validCodes() {
		AccountType[] accountTypes = values();
		String[] codes = new String[accountTypes.length];

		for (int i = 0; i < codes.length; i++) {
			codes[i] = accountTypes[i].code;
		}

		return codes;
	}
}
