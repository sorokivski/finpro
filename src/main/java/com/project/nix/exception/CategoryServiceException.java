package com.project.nix.exception;

public class CategoryServiceException extends RuntimeException {
    public static final String WRONG_CATEGORY_TYPE = "Wrong category type";
    public static final String REMOVE_FORBIDDEN = "There`s no such category";
    public static final String DUPLICATE_CATEGORY = "Duplicate category, that one already exists";


    public CategoryServiceException(String message) {
        super(message);
    }
}
