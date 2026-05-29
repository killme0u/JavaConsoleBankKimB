package banking.step04;

import java.util.Objects;

abstract public class Account {
	private String accountNo;
	private String customerName;
	private int balance;
	private int interest;

	public Account(String accountNo, String customerName, int balance, int interest) {
		super();
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.balance = balance;
		this.interest = interest;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		String str = """
계좌번호> %s
고객이름> %s
잔고> %d
기본이자> %d%%
				""";
		return String.format(str, getAccountNo(), getCustomerName(), getBalance(), getInterest());
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountNo, other.accountNo);
	}

	// 입금액과 기본이자, 추가이자로 잔금 계산하여 계좌의 잔금 업데이트
	abstract public void depositBalance(Integer valueOf);
}
