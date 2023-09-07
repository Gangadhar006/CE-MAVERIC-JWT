package org.maveric.currencyexchange.constants;

public class ApiEndpointConstants {
    private ApiEndpointConstants(){}
    public static final String ACCOUNT_URL_PREFIX = "/account";
    public static final String ACCOUNT_CREATE_URL = "/create/{customerId}";
    public static final String ACCOUNT_UPDATE_URL = "/update/{customerId}/{accountNumber}";
    public static final String ACCOUNT_FETCHBY_CUSTOMERID_URL = "/fetchALL/{customerId}";
    public static final String ACCOUNT_FETCHBY_ACCOUNT_NUMBER_URL = "/fetch/{customerId}/{accountNumber}";
    public static final String ACCOUNT_DELETE_URL = "/delete/{customerId}/{accountNumber}";
    public static final String CUSTOMER_URL_PREFIX = "/customer";
    public static final String CUSTOMER_CREATE_URL = "/create";
    public static final String CUSTOMER_UPDATE_URL = "/update/{customerId}";
    public static final String CUSTOMER_FETCHALL_URL = "/fetchAll";
    public static final String CUSTOMER_FETCHBY_CUSTOMERID_URL = "/fetch/{customerId}";
    public static final String CUSTOMER_DELETE_URL = "/delete/{customerId}";

    public static final String EXCHANGE_RATES_URL_PREFIX = "/exchange-rates";
    public static final String EXCHANGE_RATES_BASECURRENCY_URL = "/{baseCurrency}";

    public static final String ORDER_URL_PREFIX = "/orders";
    public static final String ORDER_WATCHLIST_URL = "/watchlist";
    public static final String ORDER_PLACE_URL = "/placeorder";

    public static final String AUTHENTICATE_URL_PREFIX = "/authenticate";
    public static final String AUTHENTICATE_LOGIN_URL = "/login";
}
