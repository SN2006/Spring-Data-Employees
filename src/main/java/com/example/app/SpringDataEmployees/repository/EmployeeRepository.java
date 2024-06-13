package com.example.app.SpringDataEmployees.repository;

import com.example.app.SpringDataEmployees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findBySurname(String surname);
    List<Employee> findByPosition(String position);
    List<Employee> findByPhone(String phone);
}
