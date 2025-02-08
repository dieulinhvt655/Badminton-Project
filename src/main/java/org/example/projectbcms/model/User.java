package org.example.projectbcms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    //id field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    //username field
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    //password field
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "role", nullable = false)
    private Integer role;

}