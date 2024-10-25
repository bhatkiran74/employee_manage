package com.employee.employee_manage.service.impl;


import com.employee.employee_manage.entity.Employee;
import com.employee.employee_manage.exception.ResourseNotFoundException;
import com.employee.employee_manage.repository.EmployeeRepository;
import com.employee.employee_manage.service.EmployeeService;
import com.employee.employee_manage.util.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        EmployeeDto savedEmployeeDto =new EmployeeDto();
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
        Employee employee= repository.findByEmployeeId(empId);

        if (employee == null){
            throw new ResourseNotFoundException("Employee","EmployeeId",empId);
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
}
