package com.example.demo.Service;

import com.example.demo.Entity.Employee;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class EmployeeService  {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findByFirstname(String firstname) {
        return employeeRepository.findByFirstname(firstname);
    }

    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    public Employee Fullupdate(String employeeName, Employee employee) {


        Employee existingEmployee = employeeRepository.findByFirstname(employeeName).orElse(null);


        existingEmployee.setFirstname(employee.getFirstname());
        existingEmployee.setLastname(employee.getLastname());
        existingEmployee.setEmail(employee.getEmail());

        //If the object has an ID that already exists in the database, Spring Updates the existing record
        return employeeRepository.save(existingEmployee);

    }

    public Employee Partialupdate(String employeeName, Employee employee) {
        Employee existingEmployee = employeeRepository.findByFirstname(employeeName).orElse(null);

        if(employee.getFirstname()!=null){
            existingEmployee.setFirstname(employee.getFirstname());
        }
        if(employee.getLastname()!=null){
            existingEmployee.setLastname(employee.getLastname());
        }
        if(employee.getEmail()!=null){
            existingEmployee.setEmail(employee.getEmail());
        }

        return employeeRepository.save(existingEmployee);
    }

}
