package com.example.demo.Entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;

@Setter
@Getter
@Document("student")
public class Employee {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;

    public Employee() {}

    public Employee(String id, String firstname, String lastName, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastName;
        this.email = email;
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
