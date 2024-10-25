package com.employee.employee_manage.util;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class TaxDeductionDto {
    private String employeeId;
    private String firstName;
    private String lastName;
    private BigDecimal yearlySalary;
    private BigDecimal taxAmount;
    private BigDecimal cessAmount;

    // Constructor
    public TaxDeductionDto(String employeeId, String firstName, String lastName,
                                BigDecimal yearlySalary, BigDecimal taxAmount, BigDecimal cessAmount) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearlySalary = yearlySalary;
        this.taxAmount = taxAmount;
        this.cessAmount = cessAmount;
    }
}
