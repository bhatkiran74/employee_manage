package com.employee.employee_manage.util;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private String doj;
    private Double salary;
    private List<String> phoneNumbers;
}
