package banking.step01;

import java.util.Scanner;

public class BankingSystemMain {

	private static final int LIST_SIZE = 100;
	static Scanner sc = new Scanner(System.in);
	private static String menuNo;
	private static Account[] accounts;
	private static int numOfAccount = 0;

	public static void main(String[] args) {
		accounts = new Account[LIST_SIZE];
		boolean isContinue = true;
		while (isContinue) {
			showMenu();
			System.out.print("선택: ");
			menuNo = sc.nextLine();

			switch (menuNo) {
			case "1":
				// 계좌 개설
				makeAccount();
				break;
			case "2":
				// 입금
				depositMoneny();
				break;
			case "3":
				// 출금
				withdrawMoneny();
				break;
			case "4":
				// 계좌 정보 출력
				showAccInfo();
				break;
			case "5":
				isContinue = false;
				break;

			default:
				break;
			}
		}
	}

	// 출금
	private static void withdrawMoneny() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String accountNo = sc.nextLine();
		System.out.print("출금액: ");
		String withdrawMoney = sc.nextLine();

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

	// 입금
	private static void depositMoneny() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String accountNo = sc.nextLine();
		System.out.print("입금액: ");
		String depositMoney = sc.nextLine();

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

	// 전체 계좌 정보 출력
	private static void showAccInfo() {
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

	private static void makeAccount() {
		System.out.println("**** 신규 계좌 개설 ****");
		System.out.print("계좌번호: ");
		String accountNo = sc.nextLine();
		System.out.print("고객 이름: ");
		String customerName = sc.nextLine();
		System.out.print("잔고: ");
		String balance = sc.nextLine();
		Account account = new Account(accountNo, customerName, Integer.valueOf(balance));
		int currentIdx = numOfAccount;
		accounts[currentIdx] = account;
		numOfAccount++;
	}

	// 메뉴 출력
	static void showMenu() {
		String menuShow = """
1. 계좌 개설
2. 입 금
3. 출 금
4. 계좌 정보 출력
5. 프로그램 종료
				""";
		System.out.printf("%s\n", menuShow);
	}

}
