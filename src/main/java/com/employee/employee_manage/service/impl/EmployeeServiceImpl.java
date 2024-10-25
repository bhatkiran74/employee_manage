package com.employee.employee_manage.service.impl;


import com.employee.employee_manage.entity.Employee;
import com.employee.employee_manage.exception.ResourseNotFoundException;
import com.employee.employee_manage.repository.EmployeeRepository;
import com.employee.employee_manage.service.EmployeeService;
import com.employee.employee_manage.util.EmployeeDto;
import com.employee.employee_manage.util.TaxDeductionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * EmployeeServiceImpl.java
 * Author: Kiransing bhat
 * Description: This class implements IEmployeeService
 **/
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        employee.setDoj(dto.getDoj());
        employee.setPhoneNumbers(dto.getPhoneNumbers());
        Employee savedEmployee = repository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto();
        savedEmployeeDto.setEmployeeId(savedEmployee.getEmployeeId());
        savedEmployeeDto.setFirstName(savedEmployee.getFirstName());
        savedEmployeeDto.setLastName(savedEmployee.getLastName());
        savedEmployeeDto.setEmail(savedEmployee.getEmail());
        savedEmployeeDto.setSalary(savedEmployee.getSalary());
        savedEmployeeDto.setDoj(savedEmployee.getDoj());
        savedEmployeeDto.setPhoneNumbers(savedEmployee.getPhoneNumbers());
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto findEmployeeByEmployeeId(String empId) {
        Employee employee = repository.findByEmployeeId(empId);

        if (employee == null) {
            throw new ResourseNotFoundException("Employee", "EmployeeId", empId);
        }
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setSalary(employee.getSalary());
        dto.setDoj(employee.getDoj());
        dto.setPhoneNumbers(employee.getPhoneNumbers());
        return dto;
    }

    @Override
    public TaxDeductionDto findEmployeesTaxDeductions(String empId) {

        Employee employee = repository.findByEmployeeId(empId);

        if (employee == null) {
            throw new ResourseNotFoundException("Employee", "EmployeeId", empId);
        }

        int monthsEmployed = calculateMonthsEmployed();


        return getTaxDeductionsForEmployee(
                empId,
                employee.getFirstName(),
                employee.getLastName(),
                BigDecimal.valueOf(employee.getSalary()),
                monthsEmployed);

    }

    private int calculateMonthsEmployed() {
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();

        // Financial year starts in April and ends in March
        if (currentMonth >= 4) {
            return now.getMonthValue() - 3; // From April to current month
        } else {
            return currentMonth + 9; // From April to March next year
        }
    }


    private BigDecimal calculateFederalTax(BigDecimal income) {
        BigDecimal tax = BigDecimal.ZERO;

        if (income.compareTo(new BigDecimal("250000")) > 0) {
            // Tax for the next 250,000 at 5%
            BigDecimal nextBracket = income.min(new BigDecimal("500000"));
            tax = nextBracket.subtract(new BigDecimal("250000")).multiply(new BigDecimal("0.05"));
        }

        if (income.compareTo(new BigDecimal("500000")) > 0) {
            // Tax for the next 500,000 at 10%
            BigDecimal nextBracket = income.min(new BigDecimal("1000000"));
            tax = tax.add(nextBracket.subtract(new BigDecimal("500000")).multiply(new BigDecimal("0.10")));
        }

        if (income.compareTo(new BigDecimal("1000000")) > 0) {
            // Tax for any amount above 1,000,000 at 20%
            tax = tax.add(income.subtract(new BigDecimal("1000000")).multiply(new BigDecimal("0.20")));
        }

        return tax;
    }

    private BigDecimal calculateCess(BigDecimal income) {
        if (income.compareTo(new BigDecimal("2500000")) > 0) {
            return income.subtract(new BigDecimal("2500000")).multiply(new BigDecimal("0.02"));
        }
        return BigDecimal.ZERO;
    }



    public TaxDeductionDto getTaxDeductionsForEmployee(String employeeId, String firstName, String lastName,
                                                       BigDecimal annualSalary, int monthsEmployed) {
        // Calculate total salary based on DOJ and months employed
        BigDecimal totalSalary = annualSalary.multiply(new BigDecimal(monthsEmployed)).divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal taxAmount = calculateFederalTax(totalSalary);
        BigDecimal cessAmount = calculateCess(totalSalary);

        return new TaxDeductionDto(employeeId, firstName, lastName, totalSalary, taxAmount, cessAmount);
    }
}
