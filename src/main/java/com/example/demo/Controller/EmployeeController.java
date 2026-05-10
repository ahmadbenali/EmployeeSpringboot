package com.example.demo.Controller;


import com.example.demo.Entity.Employee;
import com.example.demo.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<Employee> getAllEmployee() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeName}")
    public Optional<Employee> getEmployeeByName(@PathVariable String employeeName) {
        //Optional used if these are no employee with required name
        // or you can use if-statement for a specific return

//        if(employeeService.findByFirstname(employeeName).isPresent()) {
//            return employeeService.findByFirstname(employeeName);
//        }
//        else throw new RuntimeException("Employee with name " + employeeName + " not found");

        //but this better, because will show you null
        return employeeService.findByFirstname(employeeName);
    }

    @PostMapping()
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable String employeeId) {
        employeeService.delete(employeeId);
    }

    @PutMapping("/{employeeName}")
    public Employee update(@PathVariable String employeeName, @RequestBody Employee employee) {
        return employeeService.Fullupdate(employeeName, employee);
    }

    @PatchMapping("/{employeeName}")
    public Employee updateEmployeeName(@PathVariable String employeeName,@RequestBody Employee employee) {
        return employeeService.Partialupdate(employeeName, employee);
    }
}
