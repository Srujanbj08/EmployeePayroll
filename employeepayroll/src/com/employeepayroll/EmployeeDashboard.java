package com.employeepayroll;

import java.util.*;

class EmployeeDashboard implements Dashboard {

    public void display(ArrayList<Payslip> payslips, Employee employee) {

        System.out.println("\n=== EMPLOYEE DASHBOARD ===");
        System.out.println("Welcome, " + employee.getName());

        System.out.println("Dashboard Type: " + this.getClass().getName());

        // Sort by net pay descending
        Collections.sort(payslips, new Comparator<Payslip>() {
            public int compare(Payslip p1, Payslip p2) {
                return (int)(p2.getNetPay() - p1.getNetPay());
            }
        });

        System.out.println("\nRecent Payslips (Top 3):");

        int count = 0;

        for (Payslip p : payslips) {

            if (count >= 3)
                break;

            System.out.println(p);

            count++;
        }

        // Calculate YTD earnings
        double total = 0;

        for (Payslip p : payslips) {
            total += p.getNetPay();
        }

        System.out.println("\nYear-To-Date Earnings: " + total);
    }
}