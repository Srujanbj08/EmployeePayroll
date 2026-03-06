package com.employeepayroll;

class Session {

    private String username;
    private long loginTime;
    private long timeoutMillis;

    public Session(String username) {

        this.username = username;
        this.loginTime = System.currentTimeMillis();

        // 5 minute session timeout
        this.timeoutMillis = 5 * 60 * 1000;
    }

    public boolean isExpired() {

        long currentTime = System.currentTimeMillis();

        return (currentTime - loginTime) > timeoutMillis;
    }

    public String toString() {
        return "Session active for user: " + username;
    }
}
