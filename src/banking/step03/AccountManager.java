package banking.step03;

import java.util.Scanner;
import static banking.step03.ICustomDefine.*;
import static banking.step03.ErrorCode.*;
import static banking.step03.AnswerOption.*;
import static banking.step03.ValidateInput.*;

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
1. 보통 계좌
2. 신용 우대 계좌
""";
		System.out.print(MENU_ACCOUNT_MAKE);
		System.out.print("선택: ");
		String accType = sc.nextLine().trim();

		while (true) {
			ErrorCode errorCode = validateAccountType(accType);
			if (errorCode == SUCCESS) {
				break;
			} else {
				System.out.print("선택: ");
				accType = sc.nextLine().trim();
			}
		}

		System.out.print("계좌번호: ");
		String accountNo = sc.nextLine().trim();
		System.out.print("고객 이름: ");
		String customerName = sc.nextLine().trim();
		System.out.print("잔고: ");
		String balance = sc.nextLine().trim();

		while (true) {
			ErrorCode errorCode = validateDeposit(balance);
			if (errorCode == SUCCESS) {
				break;
			} else {
				System.out.print("잔고: ");
				balance = sc.nextLine().trim();
			}
		}

		System.out.print("기본이자 % (정수 형태로 입력): ");
		String interest = sc.nextLine().trim();
		String creditGrade;

		Account account = null;

		switch (accType) {
		case ACCOUNT_NORMAL:
			account = new NormalAccount(accountNo, customerName,
				convertCurrency(balance), convertCurrency(interest));
			break;
		case ACCOUNT_HIGH_CREDIT:
			System.out.print("신용등급 (A, B, C 등급): ");
			creditGrade = sc.nextLine().trim();
			account = new HighCreditAccount(accountNo, customerName,
				convertCurrency(balance), convertCurrency(interest), creditGrade);
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
		String accountNo = sc.nextLine().trim();
		System.out.print("입금액: ");
		String depositMoney = sc.nextLine().trim();

		// 입금액 유효성 검사
		while (true) {
			ErrorCode errorCode = validateDeposit(depositMoney);
			if (errorCode == SUCCESS) {
				break;
			} else if (errorCode == NOT_VALID_DEPOSIT_UNIT) {
				System.out.println("1000, 1500원 형식으로 500원 단위로 입금이 가능합니다.");
				break;
			} else {
				System.out.print("입금액: ");
				depositMoney = sc.nextLine().trim();
			}
		}

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
		String accountNo = sc.nextLine().trim();
		System.out.print("출금액: ");
		String withdrawMoney = sc.nextLine().trim();

		// 계좌번호로 조회하여 잔고 조회
		int currentBalance = retrieveBalance(accountNo);

		String isWithdrawAll = NO.getLabel();

		// 입금액 유효성 검사
		while (true) {
			ErrorCode errorCode = validateWithdraw(withdrawMoney, currentBalance);
			if (errorCode == SUCCESS) {
				break;
			} else if (errorCode == ErrorCode.NOT_VALID_WITHDRAW_UNIT) {
				System.out.println("1000, 2000원 형식으로 1000원 단위로 출금이 가능합니다.");
				break;
			} else if (errorCode == INSUFFICIENT_BALANCE) {
				System.out.print("금액 전체를 출금할까요? ");
				isWithdrawAll = sc.nextLine().trim();
				if (isWithdrawAll.equalsIgnoreCase(YES.getLabel())) {
					withdrawMoney = Integer.toString(currentBalance);
					break;
				}
			} else {
				System.out.print("출금액: ");
				withdrawMoney = sc.nextLine().trim();
			}
		}

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

	private static int retrieveBalance(String accountNo) {
		Account account = null;
		for (int i = 0; i < numOfAccount; i++) {
			account = accounts[i];
			if (account.getAccountNo().equals(accountNo)) {
				return account.getBalance();
			}
		}
		return 0;
	}

}
