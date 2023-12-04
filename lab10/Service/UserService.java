package com.example.lab10.Service;


import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUser(){
        return userRepository.findAll();

    }


    public void addUser(User user){
        userRepository.save(user);
    }


    public Boolean updateUser(Integer id , User user) {

        Boolean isUpdated = userRepository.existsById(id);
        User oldUser = userRepository.getById(id);
        if (isUpdated) {

            oldUser.setName(user.getName());
            oldUser.setAge(user.getAge());
            oldUser.setRole(user.getRole());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }


    public Boolean deleteUser(Integer id){

        Boolean isDeleted= userRepository.existsById(id);
        User user = userRepository.getById(id);
        if(isDeleted){
            userRepository.delete(user);
            return true;
        }
        return false;
    }

}


