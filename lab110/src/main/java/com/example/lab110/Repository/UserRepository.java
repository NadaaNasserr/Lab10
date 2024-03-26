package com.example.lab110.Repository;

import com.example.lab110.Model.JobApplication;
import com.example.lab110.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}