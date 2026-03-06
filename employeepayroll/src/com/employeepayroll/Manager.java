package com.employeepayroll;

class Manager extends User {

    public Manager(String username, String password) {
        super(username, password, "MANAGER");
    }

    @Override
    public boolean authenticate(String username, String password) {

        String hash = PasswordUtil.hash(password);

        return this.username.equals(username) && this.passwordHash.equals(hash);
    }
}
