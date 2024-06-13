package com.example.app.SpringDataEmployees.service;

import com.example.app.SpringDataEmployees.entity.Employee;
import com.example.app.SpringDataEmployees.exceptions.EmployeeDataException;
import com.example.app.SpringDataEmployees.exceptions.EmployeeNotFoundException;
import com.example.app.SpringDataEmployees.repository.EmployeeRepository;
import com.example.app.SpringDataEmployees.util.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeValidator validator;

    @Autowired
    public EmployeeService(EmployeeRepository repository, EmployeeValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Employee> findAll(){
        return repository.findAll();
    }

    public List<Employee> findByName(String name){
        return repository.findByName(name);
    }

    public List<Employee> findBySurname(String surname){
        return repository.findBySurname(surname);
    }

    public List<Employee> findByPosition(String position){
        return repository.findByPosition(position);
    }

    public List<Employee> findByPhone(String phone){
        return repository.findByPhone(phone);
    }

    public Optional<Employee> findById(Long id){
        return repository.findById(id);
    }

    public Employee save(Employee employee) throws EmployeeDataException {
        Map<String, String> errors = validator.validate(employee);
        if (!errors.isEmpty()) {
            throw new EmployeeDataException("Invalid data", errors);
        }
        return repository.save(employee);
    }

    public Employee update(Long id, Employee employee)
            throws EmployeeDataException, EmployeeNotFoundException {
        Map<String, String> errors = validator.validate(employee);
        if (!errors.isEmpty()) {
            throw new EmployeeDataException("Invalid data", errors);
        }
        if (findById(id).isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        employee.setId(id);
        return repository.save(employee);
    }

    public void deleteById(Long id) throws EmployeeNotFoundException{
        if (findById(id).isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        repository.deleteById(id);
    }
}
