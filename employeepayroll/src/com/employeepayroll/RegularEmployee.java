package com.employeepayroll;

class RegularEmployee extends User {

    public RegularEmployee(String username, String password) {
        super(username, password, "EMPLOYEE");
    }

    @Override
    public boolean authenticate(String username, String password) {

        String hash = PasswordUtil.hash(password);

        return this.username.equals(username) && this.passwordHash.equals(hash);
    }
}
