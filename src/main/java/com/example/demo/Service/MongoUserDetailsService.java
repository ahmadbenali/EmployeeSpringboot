package com.example.demo.Service;

import com.example.demo.Entity.Employee;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//to load user from database
@Service
public class MongoUserDetailsService implements UserDetailsService {

    private final EmployeeRepository EmployeeRepository;

    public MongoUserDetailsService(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = EmployeeRepository.findByFirstname(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Convert roles list → GrantedAuthority array
        String[] roles = employee.getRoles().toArray(new String[0]);

        return org.springframework.security.core.userdetails.User.builder()
                .username(employee.getFirstname())
                .password(employee.getPassword())
                .roles(roles)
                .build();
    }
}
