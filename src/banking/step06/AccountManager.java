package banking.step06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;
import static banking.step06.ICustomDefine.*;
import static banking.step06.ErrorCode.*;
import static banking.step06.AnswerOption.*;
import static banking.step06.AccountType.*;
import static banking.step06.ValidateInput.*;

public class AccountManager {

	static final int LIST_SIZE = 100;
	static final String DATA_FILE_PATH = "src/banking/step06/AccountInfo.obj";
	static HashSet<Account> accountSet;
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
3. 특판 계좌
""";
		System.out.print(MENU_ACCOUNT_MAKE);
		System.out.print("선택: ");
		String accTypeNo = sc.nextLine().trim();

		while (true) {
			ErrorCode errorCode = validateAccountType(accTypeNo);
			if (errorCode == SUCCESS) {
				break;
			} else {
				System.out.print("선택: ");
				accTypeNo = sc.nextLine().trim();
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

		AccountType accType = fetchAccountType(accTypeNo);

		switch (accType) {
		case NORMAL:
			account = new NormalAccount(accountNo, customerName,
				convertCurrency(balance), convertCurrency(interest));
			break;
		case HIGH_CREDIT:
			System.out.print("신용등급 (A, B, C 등급): ");
			creditGrade = sc.nextLine().trim();
			account = new HighCreditAccount(accountNo, customerName,
				convertCurrency(balance), convertCurrency(interest), creditGrade);
			break;
		case SPECIAL:
			account = new SpecialAccount(accountNo, customerName, numOfAccount, LIST_SIZE);
			break;
		}

		// 계좌번호가 동일한 경우 중복된 계좌로 간주
		if (isDuplicateAccount(account)) {
			// YES: 기존 계좌 정보를 삭제하고 덮어쓴다.
			System.out.println("계좌번호 동일 - 중복계좌");
			System.out.print("덮어 쓸까요 (y 혹은 n)? ");
			// NO: 기존 계좌 정보를 유지한다.
			String isOverwrite = sc.nextLine().trim();
			if (isYes(isOverwrite)) {
				accountSet.remove(account);
				accountSet.add(account);
			}
		} else {
			accountSet.add(account);
		}
	}

	private static boolean isDuplicateAccount(Account account) {
		return accountSet.contains(account);
	}

	// 전체 계좌 정보 출력
	static void showAccInfo() {
		System.out.println("**** 계좌 정보 출력 ****");
		for (Account account : accountSet) {
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
				System.out.print("입금액: ");
				depositMoney = sc.nextLine().trim();
			} else {
				System.out.print("입금액: ");
				depositMoney = sc.nextLine().trim();
			}
		}

		// 해당 계좌번호의 객체를 불러온다.
		for (Account account : accountSet) {
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
			} else if (errorCode == NOT_VALID_WITHDRAW_UNIT) {
				System.out.println("1000, 2000원 형식으로 1000원 단위로 출금이 가능합니다.");
				System.out.print("출금액: ");
				withdrawMoney = sc.nextLine().trim();
			} else if (errorCode == INSUFFICIENT_BALANCE) {
				System.out.print("금액 전체를 출금할까요? ");
				isWithdrawAll = sc.nextLine().trim();
				if (isYes(isWithdrawAll)) {
					withdrawMoney = Integer.toString(currentBalance);
					break;
				}
			} else {
				System.out.print("출금액: ");
				withdrawMoney = sc.nextLine().trim();
			}
		}

		for (Account account : accountSet) {
			if (account.getAccountNo().equals(accountNo)) {
				account.setBalance(account.getBalance() - Integer.valueOf(withdrawMoney));
				System.out.println("출금이 완료 되었습니다.");
				break;
			}
		}

		showAccInfo();
	}

	// 계좌 정보 삭제
	public static void deleteAccInfo() {
		System.out.println("삭제할 계좌번호를 입력하세요.");
		System.out.print("계좌번호: ");
		String accountNo = sc.nextLine().trim();

		accountSet.stream()
			.filter(acc -> acc.getAccountNo().equals(accountNo))
			.findFirst()
			.ifPresentOrElse(
				deletedAcc -> {
//					System.out.printf("삭제할 계정 정보:\n %s\n", deletedAcc);
					accountSet.remove(deletedAcc);
					System.out.printf("계좌번호 %s인 계좌가 삭제되었습니다.\n\n", accountNo);
				},
				() -> {
					System.err.println("********************************");
					System.err.printf("계좌를 찾을 수 없습니다.", accountNo);
					System.err.println("\n********************************");
					System.out.println();
				});
	}

	// 계좌번호로 잔고 조회
	private static int retrieveBalance(String accountNo) {
		for (Account account : accountSet) {
			if (account.getAccountNo().equals(accountNo)) {
				return account.getBalance();
			}
		}
		return 0;
	}

	public static boolean readAccountsIntoObject() {
		File objFile = new File(DATA_FILE_PATH);
		if (objFile.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(objFile));

				Object obj = null;
				while (!(((obj = ois.readObject())) instanceof EofMarker)) {
					accountSet.add((Account) obj);
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	// 계좌 정보를 파일로 저장
	public static boolean saveAccountsIntoObject() {
		File objFile = new File(DATA_FILE_PATH);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(objFile));
			for (Account account : accountSet) {
				oos.writeObject(account);
			}
			oos.writeObject(new EofMarker());
			oos.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
