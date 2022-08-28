package com.essoft.JavaSpringPracticum.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="first_name",nullable = false)
    @Size(max = 50)
    private String firstName;

    @Column(name="last_name", nullable = false)
    @Size(max = 50)
    private String lastName;

    @Column(name="email", nullable = false)
    @Size(max = 50)
    private String email;

    @Column(name="phone_number", nullable = false)
    @Size(max = 15)
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
