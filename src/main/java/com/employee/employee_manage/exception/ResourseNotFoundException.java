package com.employee.employee_manage.exception;

public class ResourseNotFoundException extends RuntimeException{
    public ResourseNotFoundException(String resourceName,String fieldName,String fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}


