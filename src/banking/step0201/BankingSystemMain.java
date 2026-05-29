package banking.step0201;

import java.util.Scanner;
import static banking.step0201.ICustomDefine.*;

public class BankingSystemMain {

	private static final int LIST_SIZE = 100;
	static Scanner sc = new Scanner(System.in);
	private static String menuNo;

	// github 연동
	// 연동 완료 후 커밋 & 푸시
	// github clone 작업 성공
	// 학원에서 pull 없이 작업함
	public static void main(String[] args) {
		AccountManager.accounts = new Account[LIST_SIZE];
		boolean isContinue = true;
		while (isContinue) {
			showMenu();
			System.out.print("선택: ");
			menuNo = sc.nextLine();

			switch (menuNo) {
			case MAKE:
				// 계좌 개설
				AccountManager.makeAccount();
				break;
			case "2":
				// 입금
				AccountManager.depositMoneny();
				break;
			case "3":
				// 출금
				AccountManager.withdrawMoneny();
				break;
			case "4":
				// 계좌 정보 출력
				AccountManager.showAccInfo();
				break;
			case "5":
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
