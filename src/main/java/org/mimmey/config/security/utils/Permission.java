package org.mimmey.config.security.utils;

public enum Permission {

    MY_PROFILE_ACTIONS("myProfileActions"),
    ADMIN_ACTIONS("adminActions"),
    BEING_AN_AUTHOR("beingAnAuthor");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
