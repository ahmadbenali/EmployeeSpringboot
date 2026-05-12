package com.example.demo.Entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Document("student")
public class Employee {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    String password;
    List<String> roles;
    private String email;

    public Employee() {}

    public Employee(String id, String firstname,String password,List<String> roles ,String lastName, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        this.roles =roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                "firstname='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
