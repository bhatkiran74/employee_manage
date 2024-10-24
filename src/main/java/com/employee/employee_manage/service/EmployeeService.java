package com.employee.employee_manage.service;

import com.employee.employee_manage.util.EmployeeDto;
import com.employee.employee_manage.util.TaxDeductionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto dto);


    EmployeeDto findEmployeeByEmployeeId(String empId);

    TaxDeductionDto findEmployeesTaxDeductions(String empId);
}
