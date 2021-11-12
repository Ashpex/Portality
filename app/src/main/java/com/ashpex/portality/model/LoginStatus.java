package com.ashpex.portality.model;

public class LoginStatus {
    private String message;
    private InfoUser user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InfoUser getUser() {
        return user;
    }

    public void setUser(InfoUser user) {
        this.user = user;
    }
}
