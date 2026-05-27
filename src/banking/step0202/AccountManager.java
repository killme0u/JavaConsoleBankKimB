package banking.step0202;

import java.util.Scanner;
import static banking.step0202.ICustomDefine.*;

public class AccountManager {

	static final int LIST_SIZE = 100;
	static Account[] accounts;
	static int numOfAccount = 0;
	static Scanner sc = new Scanner(System.in);

	// 개좌 개설
	/* - 보통계좌와 신용신뢰계좌로 분리 후 소스 변경
	 *
	 * 1. 계좌 선택: 1.보통계좌, 2.신용신뢰계좌
	 * 2. 계좌 선택 입력 처리
	 * 3. 보통계좌 입력 폼 구성
	 * 4. 보통계좌 개설 입력 처리
	 * 5. 신용신뢰계좌 입력 폼 구성
	 * 6. 신용신뢰계좌 개설 입력 처리
	 */
	static void makeAccount() {
		String MENU_ACCOUNT_MAKE = """
**** 신규 계좌 개설 ****
----- 계좌 선택 -----
1. 보통계좌
2. 신용신뢰계좌
""";
		System.out.print(MENU_ACCOUNT_MAKE);
		System.out.print("선택: ");
		String accType = sc.nextLine();

		System.out.print("계좌번호: ");
		String accountNo = sc.nextLine();
		System.out.print("고객 이름: ");
		String customerName = sc.nextLine();
		System.out.print("잔고: ");
		String balance = sc.nextLine();
		System.out.print("기본이자 % (정수 형태로 입력): ");
		String interest = sc.nextLine();
		String creditGrade;

		Account account = null;

		switch (accType) {
		case ACCOUNT_NORMAL:
			account = new NormalAccount(accountNo, customerName,
				Integer.valueOf(balance), Integer.valueOf(interest));
			break;
		case ACCOUNT_HIGH_CREDIT:
			System.out.print("신용등급 (A, B, C 등급): ");
			creditGrade = sc.nextLine();
			account = new HighCreditAccount(accountNo, customerName,
				Integer.valueOf(balance), Integer.valueOf(interest), creditGrade);
			break;
		}

		System.out.println(account);

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
			System.out.println(account);
			System.out.println("---------------------------");
		}
		System.out.println("전체 계좌 정보 출력이 완료되었습니다.");
	}

	// 입금
	static void depositMoneny() {
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
				// 입금 처리
				account.depositBalance(Integer.valueOf(depositMoney));
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

}
