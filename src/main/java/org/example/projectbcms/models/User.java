package org.example.projectbcms.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {

    //id field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    //username field
    @Column(name = "username", length = 50)
    private String username;

    //password field
    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "role", nullable = false)
    private Integer role;

}