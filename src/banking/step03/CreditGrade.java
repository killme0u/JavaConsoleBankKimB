package banking.step03;

public enum CreditGrade {
	A(7),
	B(4),
	C(2);

	private final int interestRate;

	CreditGrade(int interestRate) {
		this.interestRate = interestRate;
	}

	public int getInterestRate() {
		return interestRate;
	}
}
