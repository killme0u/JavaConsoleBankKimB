package banking.step06;

import java.util.Arrays;
import java.util.Set;

public enum AnswerOption {
	YES("yes", Set.of("y", "yes")),
	NO("no", Set.of("n", "no"));

	private final String label;
	private final Set<String> validInputs;

	AnswerOption(String label, Set<String> validInputs) {
		this.label = label;
		this.validInputs = validInputs;
	}

	public String getLabel() {
		return label;
	}

	public static AnswerOption fromInput(String input) {
		if (input == null) {
			throw new IllegalArgumentException("입력값이 null 입니다.");
		}
		String normalized = input.strip().toLowerCase();

		return Arrays.stream(values())
				.filter(opt -> opt.validInputs.contains(normalized))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						"유효하지 않은 입력입니다: " + input));
	}

	public static boolean isYes(String input) {
		try {
			return fromInput(input) == YES;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
