package com.project.nix.exception;

public class UserServiceException extends RuntimeException
{
    public static final String EXISTS_USER_BY_EMAIL = "Exists user by email";
    public static final String WRONG_EMAIL_OR_PASSWORD = "Wrong email or password";
    public static final String WRONG_AUTHENTICATION_DATA = "Wrong authentication data";

    public UserServiceException(String message) {
        super(message);
    }
}
