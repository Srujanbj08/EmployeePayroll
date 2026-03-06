package com.employeepayroll;

class SalaryComponents {

    double basicSalary;
    double hra;
    double da;
    double allowances;
    double pf;
    double tax;
    double netPay;

    public SalaryComponents(double basicSalary, double hra, double da, double allowances) {

        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.allowances = allowances;
    }
}
