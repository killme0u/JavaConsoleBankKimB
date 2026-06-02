package banking.step06;

public class SpecialAccount extends NormalAccount {

	private static final long serialVersionUID = -5939286676080614875L;

	// 마지막 입금 회차
	private int lastInstallment;

	public SpecialAccount(String accountNo, String customerName, int balance, int interest) {
		super(accountNo, customerName, balance, interest);
	}

	@Override
	public void depositBalance(Integer depositMoney) {
		// 보통예금계좌 잔고 계산 후 반영
		super.depositBalance(depositMoney);
		// 1. 보통예금계좌잔고: [잔고 + (잔고 * 기본이자) + 입금액]으로 반영한 잔고 다시 읽음
		// => getBalance()
		// 2. 특판계좌: 보통예금계좌잔고 + (짝수 번째 입금 회차 시 500원 추가)
		int specialBalance = getBalance() + (lastInstallment % 2 == 0 ? 500 : 0);
		setBalance(specialBalance);
	}

	@Override
	public String toString() {
		return String.format("%s\n입금 회차> %d차",
			super.toString(), lastInstallment);
	}

	public int getLastInstallment() {
		return lastInstallment;
	}

	public void setLastInstallment(int lastInstallment) {
		this.lastInstallment = lastInstallment;
	}
}
