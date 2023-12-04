package com.example.lab10.Controller;


import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/JobApplication")
@AllArgsConstructor
public class JobApplicationController {


    private final JobApplicationService jobApplicationService;


    @GetMapping("/get")
    public ResponseEntity getAllJobApplication(){

        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getAllJobApplication());
    }


    @PostMapping("/add")
    public ResponseEntity addJobApplication(@RequestBody @Valid JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        String jobApplication1 = jobApplicationService.addJobApplication(jobApplication);

        if(jobApplication1.equals("OK")){
            jobApplicationService.addJobApplication(jobApplication);
            return ResponseEntity.status(200).body(new ApiResponse("job Application added"));

        }
        if(jobApplication1.equals("user id not found")){
            return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
        }
        else  return ResponseEntity.status(400).body(new ApiResponse("job post id not found"));

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteApplication(@PathVariable Integer id) {
        Boolean isDeletes = jobApplicationService.WithdrawJobApplication(id);
        if(isDeletes){
            return ResponseEntity.status(200).body(new ApiResponse("job Application deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
    }
}
