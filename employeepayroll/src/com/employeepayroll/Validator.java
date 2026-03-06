package com.employeepayroll;

import java.util.regex.*;

class Validator {

    public static void validateEmail(String email) throws ValidationException {

        String pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!Pattern.matches(pattern, email)) {
            throw new ValidationException("Invalid Email Format");
        }
    }

    public static void validatePhone(String phone) throws ValidationException {

        String pattern = "^[6-9][0-9]{9}$";

        if (!Pattern.matches(pattern, phone)) {
            throw new ValidationException("Invalid Phone Number");
        }
    }

    public static void validateEmpId(String empId) throws ValidationException {

        String pattern = "^EMP-[0-9]{4}$";

        if (!Pattern.matches(pattern, empId)) {
            throw new ValidationException("Employee ID must follow EMP-XXXX format");
        }
    }
}
