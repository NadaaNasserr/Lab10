package com.example.lab110.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 4)
    @Column(columnDefinition = "varchar(50) not null ")
    private String title;
    @NotEmpty(message = "Description Cannot be empty")
    @Column(columnDefinition = "varchar(150) not null")
    private String description;
    @NotEmpty(message = "Location Cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;
    @NotNull(message = "salary cannot be empty")
    @Positive
    @Column(columnDefinition = "int not null ")
    private Integer salary;
    @Column(columnDefinition = "DATE")
    private LocalDate postingDate;
}