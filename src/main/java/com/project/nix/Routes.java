package com.project.nix;

import java.util.UUID;

public final class Routes {

    private Routes() {
        throw new AssertionError("non-instantiable class");
    }

    public static final String API_ROOT = "/api/v1";

    public static final String USERS = API_ROOT + "/users";
    public static final String BILLS = API_ROOT + "/bills";
    public static final String CATEGORIES = API_ROOT + "/categories";

    public static final String TOKEN = API_ROOT + "/token";
    public static String user(long id) {
        return USERS + '/' + id;
    }

}