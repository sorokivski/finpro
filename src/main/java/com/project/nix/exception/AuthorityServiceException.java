package com.project.nix.exception;

public class AuthorityServiceException extends RuntimeException {


    public static final String AUTHORITY_NOT_FOUND = "Authority not found";

    public AuthorityServiceException(String message)
    {
        super(message);
    }
}
