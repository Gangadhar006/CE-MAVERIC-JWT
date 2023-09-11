package org.maveric.currencyexchange.constants;

public class ExceptionConstants {
    private ExceptionConstants() {
    }

    public static final String CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE = "customer not found with the given id";
    public static final String FORBIDDEN_ACCESS_EXCEPTION_MESSAGE = "Access Forbidden";
    public static final String ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE = "account not found with the given id";
    public static final String ACCOUNT_MISMATCH_EXCEPTION_MESSAGE = "account mis match";
    public static final String INSUFFICIENT_FUNDS_EXCEPTION_MESSAGE = "insufficient funds";
    public static final String ACCOUNT_CONFLICT_EXCEPTION_MESSAGE = "source & destination accounts should be different";
    public static final String CREDENTIALS_CHECK_MESSAGE = "Invalid email or password.";
    public static final String ACCOUNT_ALREADY_EXISTS_MESSAGE = "Can't create Account, Account already Exists!";
}
