package com.example.lab110.Controller;


import com.example.lab110.ApiResponse.ApiResponse;
import com.example.lab110.Model.User;
import com.example.lab110.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
        private final UserService userService;

        @GetMapping("/get")
        public ResponseEntity getUsers(){
            return ResponseEntity.ok(userService.getUsers());
        }

        @PostMapping("/add")
        public ResponseEntity addUsers(@RequestBody @Valid User user, Errors errors){
            if (errors.hasErrors()){
                String message=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.badRequest().body(message);
            }
            userService.addUsers(user);
            return ResponseEntity.ok().body(new ApiResponse("User Added"));
        }
        @PutMapping("/update/{uId}")
        public ResponseEntity updateUsers(@PathVariable Integer uId, @RequestBody @Valid User user, Errors errors){
            if (errors.hasErrors()){
                String message=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.badRequest().body(message);
            }
            Boolean isUpdated= userService.updateUser(uId,user);
            if (isUpdated){
                return ResponseEntity.ok().body(new ApiResponse("User Updated"));
            }
            return ResponseEntity.badRequest().body(new ApiResponse("User ID not found"));
        }
        @DeleteMapping("/delete/{uId}")
        public ResponseEntity deleteUsers(@PathVariable Integer uId){
            Boolean isDeleted=userService.deleteUser(uId);
            if (isDeleted){
                return ResponseEntity.ok().body(new ApiResponse("User Deleted"));
            }
            return ResponseEntity.badRequest().body(new ApiResponse("User ID not found"));
        }

    }
