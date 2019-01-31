package com.opinous.constants;

public class URLMappings {

    //File mappings
    public static final String DOWNLOAD_FILE = "/file/{fileName:.+}";

    //User mappings
    public static final String USER_REGISTRATION = "/registration";
    public static final String USER_LOGIN = "/login";
    public static final String USER_HOME = "/";

    //Room mappings
    public static final String ROOM = "/room";
    public static final String ROOM_NEW = "/new";

    //Admin
    public static final String ADMIN = "/admin";
    public static final String NEW_ADMIN = "/new/admin";
    public static final String LIST_ADMINS = "/list/admins";
    public static final String NEW_ANON = "/new/anon";
    public static final String UPDATE_ANON = "/update/anon";
    public static final String LIST_ANON = "/list/anon";
    public static final String NEW_MODERATOR = "/new/moderator";
    public static final String LIST_MODERATOR = "/list/moderators";
    public static final String NEW_USER = "/new/user";
    public static final String UPDATE_USER = "/update/user";
    public static final String LIST_USER = "/list/users";
}
