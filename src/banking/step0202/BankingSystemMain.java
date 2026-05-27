package banking.step0202;

import static banking.step0202.ICustomDefine.*;

public class BankingSystemMain {

	private static String menuNo;

	public static void main(String[] args) {
		AccountManager.accounts = new Account[AccountManager.LIST_SIZE];
		boolean isContinue = true;
		while (isContinue) {
			showMenu();
			System.out.print("선택: ");
			menuNo = AccountManager.sc.nextLine();

			switch (menuNo) {
			case MAKE:
				// 계좌 개설
				AccountManager.makeAccount();
				break;
			case DEPOSIT:
				// 입금
				AccountManager.depositMoneny();
				break;
			case WITHDRAW:
				// 출금
				AccountManager.withdrawMoneny();
				break;
			case INQUIRE:
				// 계좌 정보 출력
				AccountManager.showAccInfo();
				break;
			case EXIT:
				isContinue = false;
				break;

			default:
				break;
			}
		}
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
