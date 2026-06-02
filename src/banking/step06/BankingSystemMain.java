package banking.step06;

import static banking.step06.AnswerOption.isYes;
import static banking.step06.ValidateInput.*;

import java.util.HashSet;

public class BankingSystemMain {

    static final String VALID_MENU_MESSAGE = "메뉴 선택은 1~5번 숫자를 입력해 주세요.";
    private static String menuNo;

    public static void main(String[] args) {
    	AccountManager.accountSet = new HashSet<Account>();

    	// 컬렉션에 저장된 계좌 정보를 저장한 파일을 load
    	AccountManager.readAccountsIntoObject();

        boolean isContinue = true;
        while (isContinue) {
            showMenu();
            System.out.print("선택: ");
            menuNo = AccountManager.sc.nextLine().trim();

            // 선택한 메뉴 번호 검사
            MenuOption menu = validateMenuNo(menuNo);
            if (menu == null) {
				continue;
			}

            switch (menu) {
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
            case DELETE:
            	// 계좌 정보 삭제
            	AccountManager.deleteAccInfo();
            	break;
            case EXIT:
            	boolean isSaveSuccess = AccountManager.saveAccountsIntoObject();
            	if (isSaveSuccess) {
            		isContinue = false;
				} else {
					System.out.println("파일 저장에 실패했습니다.");
					System.out.print("프로그램을 종료하시겠습나까(y 혹은 n)? ");
					String isExitProgram = AccountManager.sc.nextLine().trim();
					if (isYes(isExitProgram)) {
						isContinue = false;
					}
				}
                break;

            default:
                break;
            }
        }
    }

	// 메뉴 출력
    static void showMenu() {
        String menuShow = """
------------------------
>>>>>> 골라~골라~ <<<<<<
------------------------
1. 계좌 개설
2. 입 금
3. 출 금
4. 계좌 정보 출력
5. 계좌 정보 삭제
6. 프로그램 종료
------------------------
""";
        System.out.printf("%s\n", menuShow);
    }

}
