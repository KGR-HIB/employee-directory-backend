package com.hiberus.employee.directory.security;

/**
 * AuthConstants.
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 */
public final class AuthConstants {
    public static final String SIGNIN_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTYyNTAyODczMSwiaWF0IjoxNjI1MDI4NzMxfQ.RKL_5vzjYaqAKAFlIUQ0ml2ZacG0YMGi4uIUXFvBU98";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String AUTHORITIES = "authorities";
    public static final String ID = "hiberusJWT";
    public static final String UNAUTHORIZED = "401";

    /**
     * Constructor.
     */
    private AuthConstants() {
    }
}
