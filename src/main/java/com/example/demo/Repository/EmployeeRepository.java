package com.example.demo.Repository;

import com.example.demo.Entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Optional<Employee> findByFirstname(String firstname);
}
