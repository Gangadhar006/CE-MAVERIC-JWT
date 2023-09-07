package org.maveric.currencyexchange.constants;

public class AppConstants {
    private AppConstants(){}
    public static final String LOGGER_POINT_CUT_PACKAGE_NAME = "execution(* org.maveric.currencyexchange.serviceimpl.*.*(..))";
    public static final String POINTCUT_NAME = "logging()";
    public static final String LOGGING_BEFORE = "Entering method: '{}' class: '{}'";
    public static final String LOGGING_AFTER = "Exiting method: '{}' class: '{}'";



    public static final String API_TITLE = "Currency Exchange API";
    public static final String API_VERSION = "1.0";
    public static final String API_DESCRIPTION = "Enables users to exchange their currency from one type to another";
    public static final String ACCOUNT_DELETE_MESSAGE="account deleted successfully";
    public static final String CUSTOMER_DELETE_MESSAGE="Customer Deleted Successfully";


    public static final String ACCOUNT_PREFIX = "ACCN";
    public static final int RANDOM_ACCOUNT_MIN = 100000;
    public static final int RANDOM_ACCOUNT_MAX = 900000;



    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";

    public static final String AMOUNT_PRECISION="%.3f";

}
