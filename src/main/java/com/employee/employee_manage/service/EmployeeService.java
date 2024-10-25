package com.employee.employee_manage.service;

import com.employee.employee_manage.util.EmployeeDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto dto);
}
