package com.employee.employee_manage.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * Employee.java
 * Author: Kiransing bhat
 * Description: This class implements Employee
 **/
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Employee {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String employeeId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String doj;
    @Column
    private Double salary;
    @Column
    private List<String> phoneNumbers;

}
