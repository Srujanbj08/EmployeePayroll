package com.employeepayroll;

import java.util.*;
import java.io.*;

public class EmployeeRegistrationApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {

            System.out.println("\n=== EMPLOYEE PAYROLL SYSTEM ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

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

                if (session != null && !session.isExpired()) {

                    System.out.println("\nLogin Successful!");

                    System.out.println("\n1. Generate Payslip");
                    System.out.println("2. Logout");

                    System.out.print("Enter option: ");
                    int option = sc.nextInt();
                    sc.nextLine();

                    if (option == 1) {

                        System.out.println("\nUSE CASE 3: PAYSLIP GENERATION");

                        System.out.print("Enter Employee ID: ");
                        String empId = sc.nextLine();

                        System.out.print("Enter Employee Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Month: ");
                        String month = sc.nextLine();

                        System.out.print("Enter Basic Salary: ");
                        double basic = sc.nextDouble();

                        System.out.print("Enter HRA: ");
                        double hra = sc.nextDouble();

                        System.out.print("Enter DA: ");
                        double da = sc.nextDouble();

                        System.out.print("Enter Allowances: ");
                        double allowances = sc.nextDouble();
                        sc.nextLine();

                        Employee emp = new Employee(empId, name);

                        PayrollService service = new PayrollService();

                        Payslip payslip = service.generatePayslip(emp, month, basic, hra, da, allowances);

                        System.out.println(payslip);

                       

                        System.out.println("\n=== USE CASE 4: PAYSLIP PRINT / DOWNLOAD ===");

                        try {

                            Payslip clonedPayslip = (Payslip) payslip.clone();

                            if (payslip.equals(clonedPayslip)) {

                                System.out.println("Verified: Download copy matches original.");

                                System.out.println("Original hashcode : " + payslip.hashCode());
                                System.out.println("Cloned hashcode   : " + clonedPayslip.hashCode());
                            }

                            DownloadToken token = new DownloadToken();

                            if (token.isExpired()) {

                                System.out.println("Download token expired.");
                                continue;
                            }

                            FileService fileService = new FileService();

                            String txtFile = fileService.savePayslipAsText(clonedPayslip);
                            String pdfFile = fileService.savePayslipAsPdf(clonedPayslip);

                            System.out.println("\nPayslip Download Successful.");
                            System.out.println("Saved as TEXT file: " + txtFile);
                            System.out.println("Saved as PDF file : " + pdfFile);

                            System.out.println("\n--- Printed Payslip ---");
                            System.out.println(clonedPayslip);

                        }
                        catch (Exception e) {

                            System.out.println("Error during payslip download.");
                        }
                    }
                }
            }

            else if (choice == 3) {
                System.out.println("Exiting system...");
            }

            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}