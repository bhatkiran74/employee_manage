package com.employee.employee_manage.controller;

import com.employee.employee_manage.service.EmployeeService;
import com.employee.employee_manage.util.EmployeeDto;
import com.employee.employee_manage.util.ErrorResponseDto;
import com.employee.employee_manage.util.TaxDeductionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    /**
     * Endpoint to create a new account based on the provided CustomerDto.
     *
     * @param dto The EmployeeDto containing employee information.
     * @return ResponseEntity with status 201 (Created) and a response body indicating success.
     */
    @Operation(summary = "Create a new Employee ", description = "Creates a new Employee based on the provided EmployeeDto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto dto) {
        return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
    }


    @Operation(summary = "Fetch Employee details", description = "Fetches Employeet details based on the provided Employee id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details fetched successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Employee id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDto> findEmployeeByEmployeeId(@PathVariable("empId") String empId) {
        EmployeeDto dto = employeeService.findEmployeeByEmployeeId(empId);
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }


    @GetMapping("/{empId}/tax-deductions")
    public ResponseEntity<TaxDeductionDto> findEmployeesTaxDeductions(@PathVariable("empId") String empId) {
        TaxDeductionDto dto = employeeService.findEmployeesTaxDeductions(empId);
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }


}
