package com.example.lab10.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @NotNull(message = "user Id must be not null")
    @Column(columnDefinition = "int not null")
    private Integer userId;


    @NotNull(message = "jobPost Id must be not null")
    @Column(columnDefinition = "int not null")
    private Integer jobPostId;
}

