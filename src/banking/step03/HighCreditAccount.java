package banking.step03;

import static banking.step0202.ICustomDefine.*;

public class HighCreditAccount extends Account {

	private String creditGrade;

	public HighCreditAccount(String accountNo, String customerName, int balance, int interest, String creditGrade) {
		super(accountNo, customerName, balance, interest);
		this.creditGrade = creditGrade;
	}

	public String getCreditGrade() {
		return creditGrade;
	}

	public void setCreditGrade(String creditGrade) {
		this.creditGrade = creditGrade;
	}

	@Override
	public String toString() {
		String str = """
신용등급: %s
				""";
		return super.toString() + String.format(str, getCreditGrade());
	}

	@Override
	public void depositBalance(Integer depositMoney) {
		int creditGradeInterest = CREDIT_C;
		switch (getCreditGrade()) {
		case "A":
			creditGradeInterest = CREDIT_A;
			break;
		case "B":
			creditGradeInterest = CREDIT_B;
			break;
		case "C":
			creditGradeInterest = CREDIT_C;
			break;
		default:
			break;
		}
		float prevBalance = (float) getBalance();
		float interest = (float) getInterest();
		// 신용신뢰계좌: 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
		int balance = (int) (prevBalance + (prevBalance * (interest / 100))
			+ (prevBalance * ((float) creditGradeInterest / 100)) + depositMoney);
		setBalance(balance);
	}


}
