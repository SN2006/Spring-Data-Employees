package com.example.app.SpringDataEmployees.util;

import com.example.app.SpringDataEmployees.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeValidator {

    private static final String PHONE_REGEX = "[0-9]{3} [0-9]{3}-[0-9]{4}";

    public Map<String, String> validate(Employee employee) {
        Map<String, String> errors = new HashMap<>();
        if (employee == null){
            errors.put("employee", Constants.EMPLOYEE_EMPTY_MSG);
            return errors;
        }
        if (employee.getName() == null || employee.getName().trim().isEmpty()){
            errors.put("name", Constants.NAME_EMPTY_MSG);
        }
        if (employee.getSurname() == null || employee.getSurname().trim().isEmpty()){
            errors.put("surname", Constants.SURNAME_EMPTY_MSG);
        }
        if (employee.getPosition() == null || employee.getPosition().trim().isEmpty()){
            errors.put("position", Constants.POSITION_EMPTY_MSG);
        }
        if (employee.getPhone() == null || employee.getPhone().trim().isEmpty()){
            errors.put("phone", Constants.PHONE_EMPTY_MSG);
        }else if(!employee.getPhone().matches(PHONE_REGEX)){
            errors.put("phone", Constants.PHONE_INVALID_MSG);
        }
        return errors;
    }

}
