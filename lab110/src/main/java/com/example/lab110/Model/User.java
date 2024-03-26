package com.example.lab110.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 4)
    @Column(columnDefinition = "varchar(25) not null ")
    private String name;
    @Email
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;
    @NotEmpty(message = "Password Cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotNull(message = "Age cannot be empty")
    @Min(21)
    @Column(columnDefinition = "int not null")
    private Integer age;
    @NotEmpty(message = "Role Cannot be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$")
    @Column(columnDefinition = "varchar(10) not null check(role='JOB_SEEKER' or role='EMPLOYER')")
    private String role;
}