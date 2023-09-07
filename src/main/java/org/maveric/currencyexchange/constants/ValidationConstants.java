package org.maveric.currencyexchange.constants;

public class ValidationConstants {
    private ValidationConstants(){}
    public static final String AMOUNT_NOTNULL_MESSAGE = "Amount is required";
    public static final String AMOUNT_POSITIVE_MESSAGE = "Amount should be Positive";
    public static final String ACCOUNT_TYPE_MESSAGE = "Account type is required";
    public static final String CURRENCY_TYPE_MESSAGE = "Currency is required";

    public static final String FIRST_NAME_MESSAGE = "First name is required";
    public static final String NAME_PATTERN_MESSAGE = "Only alphabetic characters are allowed";
    public static final String FIRST_NAME_PATTERN = "^[A-Za-z]+$";

    public static final String LAST_NAME_MESSAGE = "Last name is required";
    public static final String LAST_NAME_PATTERN_MESSAGE = "Only alphabetic characters are allowed";
    public static final String LAST_NAME_PATTERN = "^[A-Za-z]+$";

    public static final String DOB_MESSAGE = "Enter valid Date of Birth";
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String EMAIL_PATTERN_MESSAGE = "Enter valid Email";
    public static final String GENDER_MESSAGE = "Gender is required";

    public static final int PHONE_LENGTH = 10;
    public static final String PHONE_MESSAGE = "phone number length should be " + PHONE_LENGTH;
    public static final String PHONE_PATTERN = "\\d+";
    public static final String PHONE_PATTERN_MESSAGE = "Phone number should contain only digits";

    public static final String PASSWORD_MESSAGE = "Password is required";
    public static final int PASSWORD_LENGTH = 8;
    public static final String PASSWORD_PATTERN_MESSAGE = "Password must be at least " + PASSWORD_LENGTH + " characters long";

    public static final String SRCACCOUNT_MESSAGE = "Source account can't be Blank";
    public static final String DESTACCOUNT_MESSAGE ="Destination account can't be Blank";
    public static final String USER_NOT_FOUND_MESSAGE="User not found";
}
