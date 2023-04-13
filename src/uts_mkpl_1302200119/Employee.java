/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_mkpl_1302200119;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Qalbun Saliim Bakhri
 */
public class Employee {
    private enum Gender {
        lakiLaki,
        perempuan
    }
    
    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate dateJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private Gender gender; //true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate date, Gender gender, boolean isForeigner) {
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.idNumber = idNumber;
            this.address = address;
            this.dateJoined = date;
            this.gender = gender;
            this.isForeigner = isForeigner;

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
            this.spouseIdNumber = idNumber;
    }

    public void addChild(String childName, String childIdNumber) {
            childNames.add(childName);
            childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {

            //Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
            LocalDate date = LocalDate.now();

            if (date.getYear() == this.dateJoined.getYear()) {
                    monthWorkingInYear = date.getMonthValue() - this.dateJoined.getMonthValue();
            }else {
                    monthWorkingInYear = 12;
            }

            return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }
    
    
    
}
