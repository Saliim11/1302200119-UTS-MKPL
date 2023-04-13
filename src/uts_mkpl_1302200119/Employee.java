/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_mkpl_1302200119;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Qalbun Saliim Bakhri
 */
public class Employee extends Identity{
    

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private boolean gender; //true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
            super(employeeId, firstName, lastName, idNumber, address);
            this.yearJoined = yearJoined;
            this.monthJoined = monthJoined;
            this.dayJoined = dayJoined;
            this.isForeigner = isForeigner;
            this.gender = gender;

            childNames = new LinkedList<String>();
            childIdNumbers = new LinkedList<String>();
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
     * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
     */

    public void setMonthlySalary(int grade) {	
            if (grade == 1) {
                    monthlySalary = 3000000;
                    if (isForeigner) {
                            monthlySalary = (int) (3000000 * 1.5);
                    }
            }else if (grade == 2) {
                    monthlySalary = 5000000;
                    if (isForeigner) {
                            monthlySalary = (int) (3000000 * 1.5);
                    }
            }else if (grade == 3) {
                    monthlySalary = 7000000;
                    if (isForeigner) {
                            monthlySalary = (int) (3000000 * 1.5);
                    }
            }
    }

    public void setAnnualDeductible(int deductible) {	
            this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {	
            this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
            this.spouseName = spouseName;
            this.spouseIdNumber = super.getIdNumber();
    }

    public void addChild(String childName, String childIdNumber) {
            childNames.add(childName);
            childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {

            //Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
            LocalDate date = LocalDate.now();

            if (date.getYear() == yearJoined) {
                    monthWorkingInYear = date.getMonthValue() - monthJoined;
            }else {
                    monthWorkingInYear = 12;
            }

            return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }
    
}
