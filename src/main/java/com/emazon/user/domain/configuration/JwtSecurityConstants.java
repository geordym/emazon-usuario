package com.emazon.user.domain.configuration;

public class JwtSecurityConstants {
    public static String SECRET_KEY = "3nKAe0ytwn6XSOf/7mI7mmyiRrdVvcl4YVy9kG6ChaI=";
    public static final String CLAIM_SUBJECT_KEY = "sub";
    public static final String CLAIM_EXPIRATION_KEY = "exp";
    public static final String CLAIM_EXPEDITION_KEY = "iat";

    public static final Long ACCESS_TOKEN_DURATION_MINUTES = 900L;
    public static final Long REFRESH_TOKEN_DURATION_MINUTES = 1400L;
    public static final String KEY_ROLE_CLAIM = "role";

}
