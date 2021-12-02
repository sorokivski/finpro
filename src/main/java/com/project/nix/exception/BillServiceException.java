package com.project.nix.exception;

public class BillServiceException extends RuntimeException
{
    public static final String WRONG_MANIPULATION_TYPE = "Wrong manipulation type";
    public static final String REMOVE_FORBIDDEN = "Remove forbidden";

    public BillServiceException(String message)
    {
        super(message);
    }
}
