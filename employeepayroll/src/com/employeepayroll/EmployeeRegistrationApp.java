package com.employeepayroll;

import java.util.*;
import java.io.*;

public class EmployeeRegistrationApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(" USE CASE 1: EMPLOYEE REGISTRATION ");

        try {

            System.out.print("Enter Employee ID (EMP-XXXX): ");
            String empId = sc.nextLine();
            Validator.validateEmpId(empId);

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            Validator.validateEmail(email);

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();
            Validator.validatePhone(phone);

            System.out.print("Create Username: ");
            String username = sc.nextLine();

            System.out.print("Create Password: ");
            String password = sc.nextLine();

            UserAccount account = new UserAccount(username, password);

            Employee emp = new Employee(empId, name, email, phone, account);

            emp.persist();

            System.out.println("\nEmployee Registered Successfully:");
            System.out.println(emp);

            System.out.println("\nData persisted in file: employee_data.txt");

        }
        catch (ValidationException e) {
            System.out.println("\nValidation Failed: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("\nError saving employee data!");
        }

        sc.close();
    }
}
