/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_mkpl_1302200119;

/**
 *
 * @author Qalbun Saliim Bakhri
 */
public class TaxFunction {
    
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
        int tax = 0;

        if (numberOfMonthWorking > 12) {
                System.err.println("More than 12 month working per year");
        }

        if (numberOfChildren > 3) {
                numberOfChildren = 3;
        }

        if (isMarried) {
                tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren * 1500000))));
        }else {
                tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
        }

        if (tax < 0) {
                return 0;
        }else {
                return tax;
        }

    }
    
}
