package com.example.lab110.Controller;


import com.example.lab110.ApiResponse.ApiResponse;
import com.example.lab110.Model.JobApplication;
import com.example.lab110.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobApplication")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(jobApplicationService.getApplications());
    }

    @PostMapping("/add")
    public ResponseEntity applyForJob(@RequestBody @Valid JobApplication application, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        Integer application1= jobApplicationService.applyForJob(application);
        if (application1==1) {
            return ResponseEntity.ok().body(new ApiResponse("Application Applied"));
        }
        if (application1==2){
            return ResponseEntity.badRequest().body(new ApiResponse("User ID not found"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Post ID not found"));
    }

    @PutMapping("/update/{jaId}")
    public ResponseEntity updateApplications(@PathVariable Integer jaId, @RequestBody @Valid JobApplication application, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        Boolean isUpdated= jobApplicationService.updateApplications(jaId,application);
        if (isUpdated){
            return ResponseEntity.ok().body(new ApiResponse("Application Updated"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Application ID not found"));

    }

    @DeleteMapping("/delete/{jaId}")
    public ResponseEntity withdrawJobApplication(@PathVariable Integer jaId){
        Boolean isDeleted=jobApplicationService.withdrawJobApplication(jaId);
        if (isDeleted){
            return ResponseEntity.ok().body(new ApiResponse("Application Deleted"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Application ID not found"));
    }
}