package com.epam.polinakrukovich.worldvision.command.type;

/**
 * {@link CommandType} class stores url-patterns for each command type.
 *
 * @author Polina Krukovich
 */
public class CommandType {
    public static final String USER_CREATE = "/user/create";
    public static final String USER_VERIFY = "/user/verify";
    public static final String USER_DELETE = "/user/delete";
    public static final String IMAGE_CREATE = "/image/create";
    public static final String IMAGE_LIST_USER = "/image/list/user";
    public static final String IMAGE_LIST_QUERY = "/image/list/query";
    public static final String IMAGE_LIST_TOP = "/image/list/top";
    public static final String IMAGE_DELETE = "/image/delete";
    public static final String LIKE_CREATE = "/like/create";
    public static final String LIKE_DELETE = "/like/delete";
    public static final String DOWNLOAD_CREATE = "/download/create";
}
