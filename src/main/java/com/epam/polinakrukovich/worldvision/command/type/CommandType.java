package com.epam.polinakrukovich.worldvision.command.type;

public enum CommandType {
    USER_CREATE("/user/create"),
    USER_READ("/user/read"),
    USER_UPDATE("/user/update"),
    USER_DELETE("/user/delete"),
    IMAGE_CREATE("/image/create"),
    IMAGE_LIST_USER("/image/list/user"),
    IMAGE_LIST_QUERY("image/list/query"),
    IMAGE_LIST_TOP("image/list/top"),
    IMAGE_DELETE("image/delete"),
    LIKE_CREATE("like/create"),
    LIKE_DELETE("like/delete"),
    DOWNLOAD_CREATE("download/create");

    private String action;

    CommandType(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }


}
