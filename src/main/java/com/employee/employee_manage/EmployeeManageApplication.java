package com.employee.employee_manage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@OpenAPIDefinition(
		info = @Info(
				title = "Employee Manage REST API Documentation",
				version = "1.0.0",
				contact =@Contact(
						name = "Kiransing Bhat",email="bhatkiran74@gmail.com")
		)
)
public class EmployeeManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManageApplication.class, args);
	}

}
