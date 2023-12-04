package com.example.lab10.Controller;

import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {


    private final UserService userService;






    @GetMapping("/get")
    public ResponseEntity getAllUser(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body("user added");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id , @RequestBody @Valid User user , Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = userService.updateUser(id,user);
        if(isUpdated){
            return ResponseEntity.status(200).body("user updated");
        }
        return ResponseEntity.status(400).body("Invalid id");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Boolean isDeletes = userService.deleteUser(id);
        if(isDeletes){
            return ResponseEntity.status(200).body("user deleted");
        }
        return ResponseEntity.status(400).body("Invalid id");
    }
}

