package com.employeepayroll;

class DownloadToken {

    private long createdTime;
    private long expiryMillis;

    public DownloadToken() {

        createdTime = System.currentTimeMillis();

        // 1 minute expiry
        expiryMillis = 60 * 1000;
    }

    public boolean isExpired() {

        long now = System.currentTimeMillis();

        return (now - createdTime) > expiryMillis;
    }
}