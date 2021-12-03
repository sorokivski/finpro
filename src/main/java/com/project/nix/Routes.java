package com.project.nix;

public final class Routes {

    public static final String API_ROOT = "/api/v1";
    public static final String USERS = API_ROOT + "/users";
    public static final String BILLS = API_ROOT + "/bills";
    public static final String CATEGORIES = API_ROOT + "/categories";
    private Routes() {
        throw new AssertionError("non-instantiable class");
    }

    public static String user(long id) {
        return USERS + '/' + id;
    }

}