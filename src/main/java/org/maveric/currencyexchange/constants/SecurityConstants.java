package org.maveric.currencyexchange.constants;

public class SecurityConstants {
    private SecurityConstants(){}
    public static final String[] ADMIN_URL = {
            "/customer/**",
            "/account/create/**",
            "/account/update/**",
            "/account/delete/**",
            "/account/fetch/**"
    };
    public static final String[] USER_URL = {
            "/orders/**",
            "/account/fetchAll/**"
    };
    public static final String[] PUBLIC_URL = {
            "/authenticate/login",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**"
    };
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";
    public static final int AUTHORIZATION_SUBSTRING_INDEX = 7;
    public static final String SECURITY_SCHEME_NAME = "bearerAuth";
    public static final String SECURITY_BEARER_FORMAT = "JWT";

    public static final String TOKEN_SIGNING_KEY = "C1A3B219C5F6E874D5A37BC7F813E9D6CE0B3F7A51612E9B3B0346A791C04C6A";
    public static final long TOKEN_EXPIRATION = 1000 * 60 * 24;

    public static final String PERMIT_ALL_URLS = "/**";
    public static final String[] CORS_ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE", "PATCH"};

    public static final String EXCHANGE_RATES_API_URL = "https://open.er-api.com/v6/latest/";
}
