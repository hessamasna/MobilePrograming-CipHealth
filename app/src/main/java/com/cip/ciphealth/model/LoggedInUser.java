package com.cip.ciphealth.model;

public class LoggedInUser {
    public static LoggedInUser loggedInUser;
    private User user;

    public LoggedInUser(User user) {
        this.user = user;
    }

    public static LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(LoggedInUser loggedInUser) {
        LoggedInUser.loggedInUser = loggedInUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
