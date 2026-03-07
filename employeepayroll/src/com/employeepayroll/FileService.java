package com.employeepayroll;

import java.io.FileWriter;
import java.io.IOException;

class FileService {

    // Save as TXT
    public String savePayslipAsText(Payslip payslip) throws IOException {

        String fileName = "Payslip_" + payslip.getEmployee() + "_"
                + System.currentTimeMillis() + ".txt";

        FileWriter fw = new FileWriter(fileName);

        fw.write(payslip.toString());

        fw.close();

        return fileName;
    }

    // Save as PDF (demo version)
    public String savePayslipAsPdf(Payslip payslip) throws IOException {

        String fileName = "Payslip_" + payslip.getEmployee() + "_"
                + System.currentTimeMillis() + ".pdf";

        FileWriter fw = new FileWriter(fileName);

        fw.write(payslip.toString());

        fw.close();

        return fileName;
    }
}
