package com.example.lab10.Model;


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



    @NotEmpty(message = "name must not be empty")
    @Size(min = 4 , message = "Length must be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "[^0-9]*"  , message = "Must contain only characters (no numbers)")
    private String name;



    @Email(message = "must be a valid format")
    @Column(columnDefinition = "varchar(225) unique")
    private String email;




    @Column(columnDefinition = "varchar(225) not null")
    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" , message = "password must contain at least one number and one uppercase, lowercase letter and special character, and at least 8 or more characters")
    private String password;


    @NotNull(message = "age must not be empty")
    @Min(value = 21 , message = "age must be more than 21.")
    @Column(columnDefinition = "int check(age > 21) ")
    @Positive(message = "age must be a number")
    private Integer age;



    @NotEmpty(message = "role must be not null")
    @Column(columnDefinition = "varchar(20) check(role='JOB_SEEKER' or role='EMPLOYER')")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$" , message = "role must match JOB_SEEKER or EMPLOYER")
    private String role;

}
