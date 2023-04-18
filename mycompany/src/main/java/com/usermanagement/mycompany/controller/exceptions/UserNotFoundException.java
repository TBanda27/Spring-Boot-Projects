package com.usermanagement.mycompany.controller.exceptions;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{

    public String resourceName;
    public String fieldName;
    public Object fieldValue;

    public UserNotFoundException(String resourceName, String fieldName, Object fieldValue){
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
