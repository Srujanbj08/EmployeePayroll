package com.employeepayroll;

public final class Payslip implements Cloneable {

    private final Employee employee;
    private final SalaryComponents components;
    private final String month;

    public Payslip(Employee employee, SalaryComponents components, String month) {
        this.employee = employee;
        this.components = components;
        this.month = month;
    }

    public Employee getEmployee() {
        return employee;
    }

    public SalaryComponents getComponents() {
        return components;
    }

    public String getMonth() {
        return month;
    }

    public double getNetPay() {
        return components.netPay;
    }

    // Simple summary (useful for dashboards)
    public String getSummary() {
        return month + " : " + components.netPay;
    }

    // Clone method for UC4 download copy
    @Override
    public Object clone() {
        return new Payslip(employee, components, month);
    }

    // Logical equality check
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Payslip)) return false;

        Payslip other = (Payslip) obj;

        return employee.getEmpId().equals(other.employee.getEmpId())
                && month.equals(other.month);
    }

    // HashCode consistent with equals
    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + employee.getEmpId().hashCode();
        result = 31 * result + month.hashCode();

        return result;
    }

    @Override
    public String toString() {

        return "\n========== PAYSLIP ==========\n"
                + "Month        : " + month + "\n"
                + "Employee ID  : " + employee.getEmpId() + "\n"
                + "Employee Name: " + employee.getName() + "\n\n"

                + "---- Earnings ----\n"
                + "Basic Salary : " + components.basicSalary + "\n"
                + "HRA          : " + components.hra + "\n"
                + "DA           : " + components.da + "\n"
                + "Allowances   : " + components.allowances + "\n\n"

                + "---- Deductions ----\n"
                + "PF           : " + components.pf + "\n"
                + "Tax          : " + components.tax + "\n\n"

                + "Net Pay      : " + components.netPay + "\n"
                + "==============================";
    }
}