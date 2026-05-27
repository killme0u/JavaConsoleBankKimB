package banking.step0202;

public class NormalAccount extends Account {

	public NormalAccount(String accountNo, String customerName, int balance, int interest) {
		super(accountNo, customerName, balance, interest);
	}

	@Override
	public String toString() {
		return super.toString();

	}

	@Override
	public void depositBalance(Integer depositMoney) {
		float prevBalance = (float) getBalance();
		float interest = (float) getInterest();
		// 일반계좌: 잔고 + (잔고 * 기본이자) + 입금액
		int balance = (int) (prevBalance  + (prevBalance  * (interest / 100)) + depositMoney);
		setBalance(balance);
	}
}
