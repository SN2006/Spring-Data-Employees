package com.example.app.SpringDataEmployees.controller;

import com.example.app.SpringDataEmployees.entity.Employee;
import com.example.app.SpringDataEmployees.exceptions.EmployeeDataException;
import com.example.app.SpringDataEmployees.exceptions.EmployeeNotFoundException;
import com.example.app.SpringDataEmployees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<Employee>> getEmployeesByName(
            @RequestParam("name") String name
    ) {
        List<Employee> employees = service.findByName(name);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find-by-position")
    public ResponseEntity<List<Employee>> getEmployeesByPosition(
            @RequestParam("position") String position
    ){
        List<Employee> employees = service.findByPosition(position);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find-by-phone")
    public ResponseEntity<List<Employee>> getEmployeesByPhone(
            @RequestParam("phone") String phone
    ){
        List<Employee> employees = service.findByPhone(phone);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find-by-surname")
    public ResponseEntity<List<Employee>> getEmployeesBySurname(
            @RequestParam("surname") String surname
    ) {
        List<Employee> employees = service.findBySurname(surname);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Optional<Employee> optional = service.findById(id);
        return optional.map(employee ->
                new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(service.save(employee), HttpStatus.CREATED);
        }catch (EmployeeDataException e){
            Map<String, Map<String, String>> body = new HashMap<>();
            body.put("errors", e.getErrors());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id,
                                            @RequestBody Employee employee) {
        try{
            return new ResponseEntity<>(service.update(id, employee), HttpStatus.OK);
        }catch (EmployeeDataException e){
            Map<String, Map<String, String>> body = new HashMap<>();
            body.put("errors", e.getErrors());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }catch (EmployeeNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        try{
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EmployeeNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
