package com.employeepayroll;

import java.util.*;
import java.io.*;

public class AuthenticationService {

    private Map<String, User> users = new HashMap<>();
    private int maxAttempts = 3;

    public AuthenticationService() {
        loadUsersFromFile();
    }

    // Load registered users from file
    private void loadUsersFromFile() {

        try {

            File file = new File("employee_data.txt");

            if (!file.exists()) {
                System.out.println("No employee file found.");
                return;
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] data = line.split(",");

                // Expected format:
                // empId,name,email,phone,username,password
                if (data.length < 6) continue;

                String username = data[4];
                String password = data[5];

                users.put(username, new RegularEmployee(username, password));
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Error loading employee data.");
        }
    }

    // Login method
    Scanner sc = new Scanner(System.in);

    public Session login(Scanner sc) {

        int attempts = 0;

        while (attempts < maxAttempts) {

            System.out.print("\nEnter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            User user = users.get(username);

            if (user != null && user.authenticate(username, password)) {

                System.out.println("\nLogin Successful!");
                System.out.println("Role: " + user.getRole());

                showDashboard(user.getRole());

                return new Session(username);
            }

            attempts++;
            System.out.println("Invalid credentials. Attempts left: " + (maxAttempts - attempts));
        }

        System.out.println("Maximum login attempts exceeded.");
        return null;
    }

    // Dashboard display
    private void showDashboard(String role) {

        System.out.println("\n======= DASHBOARD =======");

        if ("EMPLOYEE".equals(role)) {
            System.out.println("Employee Dashboard");
            System.out.println("View Payslip | Update Profile");
        }
    }
}