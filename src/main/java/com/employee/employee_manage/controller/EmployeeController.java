package com.employee.employee_manage.controller;

import com.employee.employee_manage.service.EmployeeService;
import com.employee.employee_manage.util.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/say")
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

    /**
     * Endpoint to create a new account based on the provided CustomerDto.
     *
     * @param dto The EmployeeDto containing employee information.
     * @return ResponseEntity with status 201 (Created) and a response body indicating success.
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto dto){
        return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
    }
    

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDto> findEmployeeByEmployeeId(@PathVariable("empId")String empId){
        EmployeeDto dto=employeeService.findEmployeeByEmployeeId(empId);
        return new ResponseEntity<>(dto,HttpStatus.FOUND);
    }


}
