package banking.step04;

import static banking.step04.ErrorCode.*;

public class ValidateInput {

	// 메뉴번호 유효성 검사
	static MenuOption validateMenuNo(String menuNo) {
		ErrorCode errorCode = SUCCESS;
	    try {
	    	Integer.parseInt(menuNo);
	    	MenuOption selected = MenuOption.from(menuNo);
	    	if (selected == null) {
	    		throw new MenuSelectException(menuNo, MenuOption.validCodes());
	    	}
	    	return selected;
	    } catch (NumberFormatException e) {
	        MenuSelectException re = new MenuSelectException(menuNo);
	        errorCode = NOT_CHAR;
	        showErrorMessage(re, errorCode.getMessage());
	    } catch (MenuSelectException mse) {
	        showErrorMessage(mse, BankingSystemMain.VALID_MENU_MESSAGE);
	    }
		return null;
	}

	// 신규 계좌 개설 시 선택하는 계좌 종류가 유효한지 검사
	static ErrorCode validateAccountType(String accountType) {
		ErrorCode errorCode = SUCCESS;
		try {
			AccountType selected = AccountType.from(accountType);
			if (selected == null) {
				throw new MenuSelectException(accountType, AccountType.validCodes());
			}
		} catch (NumberFormatException e) {
			errorCode = NOT_CHAR;
			MenuSelectException mse = new MenuSelectException(accountType);
			showErrorMessage(mse, "");
		} catch (MenuSelectException e) {
			errorCode = NOT_SPECIFIC_DIGIT;
			showErrorMessage(e, "");
		}
		return errorCode;
	}

	// 입금액 유효성 검사
	static ErrorCode validateDeposit(String deposit) {
		ErrorCode errorCode = SUCCESS;
		try {
			int depositAmount = parseCurrencyAmount(deposit);
			// 입금액 유효성 검사 - 음수 금액
			if (depositAmount < 0) {
				errorCode = NOT_MINUS_DEPOSIT;
				throw new CurrencyException(deposit, NOT_MINUS_DEPOSIT);
			}
			// 입금 가능한 단위 검사
			if (depositAmount % 500 != 0) {
				errorCode = NOT_VALID_DEPOSIT_UNIT;
				throw new CurrencyException(deposit, NOT_VALID_DEPOSIT_UNIT);
			}
		} catch (CurrencyException e) {
			showErrorMessage(e);
		}
		return errorCode;
	}

	// 출금액 유효성 검사
	static ErrorCode validateWithdraw(String withdraw, int currentBalance) {
		ErrorCode errorCode = SUCCESS;
		try {
			int withdrawAmount = parseCurrencyAmount(withdraw);
			if (withdrawAmount < 0) {
				errorCode = NOT_MINUS_WITHDRAW;
				throw new CurrencyException(withdraw, NOT_MINUS_WITHDRAW);
			}
			// 출금액보다 잔고가 많은지 유효성 검사
			if (withdrawAmount > currentBalance) {
				errorCode = INSUFFICIENT_BALANCE;
				throw new CurrencyException(withdraw, INSUFFICIENT_BALANCE);
			}
			// 출금 가능한 단위 검사
			if (withdrawAmount % 1000 != 0) {
				errorCode = NOT_VALID_WITHDRAW_UNIT;
				throw new CurrencyException(withdraw, NOT_VALID_WITHDRAW_UNIT);
			}
		} catch (CurrencyException e) {
			showErrorMessage(e);
		}
		return errorCode;
	}

	static int convertCurrency(String currency) {
		int intCurrency = 0;
		try {
			intCurrency = parseCurrencyAmount(currency);
		} catch (CurrencyException e) {
			showErrorMessage(e);
		}
		return intCurrency;
	}

	// 금액 문자열을 숫자로 변환
	private static int parseCurrencyAmount(String deposit) throws CurrencyException {
		if (!isValidCurrencyAmountFormat(deposit)) {
			throw new CurrencyException(deposit, NOT_CURRENCY_CHAR);
		}
		return Integer.parseInt(deposit.replace(",", ""));
	}

	// 금액 문자열 유효성 검사
	private static boolean isValidCurrencyAmountFormat(String currencyAmount) {
		if (currencyAmount == null || currencyAmount.isBlank()) {
			return false;
		}

		return currencyAmount.matches("^-?\\d{1,3}(,\\d{3})*$|^\\d+$");
	}

	// 에러 메시지 출력
	static void showErrorMessage(MenuSelectException mse, String addMessage) {
	    System.out.println();
	    System.err.printf("%s\n - %s", mse.getMessage(), addMessage);
	    System.out.println();
	}

	static void showErrorMessage(CurrencyException ce) {
		System.out.println();
		System.err.printf("%s\n", ce.getMessage());
		System.out.println();
	}

}
