package banking.step0201;

public class AccountManager {

	static Account[] accounts;
	static int numOfAccount = 0;

	// 계좌 개설
	static void makeAccount() {
		System.out.println("**** 신규 계좌 개설 ****");
		System.out.print("계좌번호: ");
		String accountNo = BankingSystemMain.sc.nextLine();
		System.out.print("고객 이름: ");
		String customerName = BankingSystemMain.sc.nextLine();
		System.out.print("잔고: ");
		String balance = BankingSystemMain.sc.nextLine();
		Account account = new Account(accountNo, customerName, Integer.valueOf(balance));
		int currentIdx = numOfAccount;
		accounts[currentIdx] = account;
		numOfAccount++;
	}

	// 전체 계좌 정보 출력
	static void showAccInfo() {
		Account account;
		System.out.println("**** 계좌 정보 출력 ****");
		for (int i = 0; i < numOfAccount; i++) {
			account = accounts[i];
			System.out.printf("계좌번호: %s\n", account.getAccountNo());
			System.out.printf("고객 이름: %s\n", account.getCustomerName());
			System.out.printf("잔고: %d\n", account.getBalance());
			System.out.println("---------------------------");
		}
		System.out.println("전체 계좌 정보 출력이 완료되었습니다.");
	}

	// 입금
	static void depositMoneny() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String accountNo = BankingSystemMain.sc.nextLine();
		System.out.print("입금액: ");
		String depositMoney = BankingSystemMain.sc.nextLine();

		Account account;
		// 해당 계좌번호의 객체를 불러온다.
		for (int i = 0; i < numOfAccount; i++) {
			account = accounts[i];
			if (account.getAccountNo().equals(accountNo)) {
				account.setBalance(account.getBalance() + Integer.valueOf(depositMoney));
				System.out.println("입금이 완료 되었습니다.");
				break;
			}
		}

		showAccInfo();
	}

	// 출금
	static void withdrawMoneny() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String accountNo = BankingSystemMain.sc.nextLine();
		System.out.print("출금액: ");
		String withdrawMoney = BankingSystemMain.sc.nextLine();

		Account account;
		// 해당 계좌번호의 객체를 불러온다.
		for (int i = 0; i < numOfAccount; i++) {
			account = accounts[i];
			if (account.getAccountNo().equals(accountNo)) {
				account.setBalance(account.getBalance() - Integer.valueOf(withdrawMoney));
				System.out.println("출금이 완료 되었습니다.");
				break;
			}
		}

		showAccInfo();
	}

}
