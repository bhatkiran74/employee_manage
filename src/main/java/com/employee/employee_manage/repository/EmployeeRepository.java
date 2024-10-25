package com.employee.employee_manage.repository;

import com.employee.employee_manage.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * EmployeeRepository.java
 * Author: Kiransing bhat
 * Description: This class implements JpaRepository
 **/
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
}
