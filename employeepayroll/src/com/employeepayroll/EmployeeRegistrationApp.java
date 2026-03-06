package com.employeepayroll;

import java.util.*;
import java.io.*;

public class EmployeeRegistrationApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== EMPLOYEE PAYROLL SYSTEM ===");

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        if (choice == 1) {

            System.out.println("\nUSE CASE 1: EMPLOYEE REGISTRATION");

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

            } 
            catch (ValidationException e) {
                System.out.println("\nValidation Failed: " + e.getMessage());
            } 
            catch (IOException e) {
                System.out.println("\nError saving employee data!");
            }
        }

        else if (choice == 2) {

            System.out.println("\nUSE CASE 2: EMPLOYEE AUTHENTICATION & LOGIN");

            AuthenticationService auth = new AuthenticationService();

            Session session = auth.login(sc);

            if (session != null) {

                System.out.println("\n" + session);

                if (!session.isExpired()) {
                    System.out.println("Session active and valid.");
                } else {
                    System.out.println("Session expired.");
                }
            }
        }

        else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}