package com.opinous.constants;

public class RestPath {
    private static final String V1 = "/v1/api";

    private static final String COMMON = V1;

    public static final String ROOM = COMMON + "/room";
    public static final String NEW = "/new";

    public static final String AUTH = COMMON + "/auth";
    public static final String GENERATE_TOKEN = "/generateToken";
    public static final String INVALIDATE_TOKEN = "/invalidate";
    public static final String ROLES = "/roles";
}
