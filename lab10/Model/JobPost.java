package com.example.lab10.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @NotEmpty(message = "title must be not null")
    @Column(columnDefinition = "varchar(20) not null")
    @Size(min = 4 , message = "title must be more than 4 characters")
    private String title;



    @NotEmpty(message = "description must be not null")
    @Column(columnDefinition = "varchar(225) not null")
    private String description;


    @NotEmpty(message = "location must be not null")
    @Column(columnDefinition = "varchar(225) not null")
    private String location;



    @NotNull(message = "salary must be not null")
    @Column(columnDefinition = "int not null")
    @Positive(message = "salary salary be a non-negative number")
    private Integer salary;



    @Column(columnDefinition = "date")
    private Date postingDate;



}
