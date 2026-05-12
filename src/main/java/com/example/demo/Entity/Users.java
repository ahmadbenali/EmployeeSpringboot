package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document("users")
public class Users {

    @Id
    String id;
    String firstname;
    String lastname;
    List<String> roles;

    public Users() {
    }

    public Users(String id, String firstname, String lastname, List<String> roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
    }
}
