package banking.step03;

public enum AnswerOption {
	YES("yes"), NO("no");

	private final String label;

	AnswerOption(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
