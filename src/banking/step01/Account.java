package banking.step01;

public class Account {
	private String accountNo;
	private String customerName;
	private int balance;

	public Account(String accountNo, String customerName, int balance) {
		super();
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.balance = balance;
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

}
