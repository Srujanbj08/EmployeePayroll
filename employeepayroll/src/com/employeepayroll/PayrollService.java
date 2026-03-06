package com.employeepayroll;

class PayrollService {

    public Payslip generatePayslip(Employee employee,
                                   String month,
                                   double basic,
                                   double hra,
                                   double da,
                                   double allowances) {

        SalaryComponents sc = new SalaryComponents(basic, hra, da, allowances);

        // Gross salary
        double gross = basic + hra + da + allowances;

        // Deductions
        sc.pf = basic * 0.12;   // Provident Fund
        sc.tax = gross * 0.10;  // Income tax

        // Net pay
        sc.netPay = gross - (sc.pf + sc.tax);

        return new Payslip(employee, sc, month);
    }
}
