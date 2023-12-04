package com.example.lab10.Controller;



import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobPost")
@AllArgsConstructor
public class JobPostController {


    private final JobPostService jobPostService;


    @GetMapping("/get")
    public ResponseEntity getAllJobPost(){

        return ResponseEntity.status(HttpStatus.OK).body(jobPostService.getAllJobPost());
    }


    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("JobPost added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id , @RequestBody @Valid JobPost jobPost , Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = jobPostService.updateJobPost(id,jobPost);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("JobPost updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        Boolean isDeletes = jobPostService.deleteJobPost(id);
        if(isDeletes){
            return ResponseEntity.status(200).body(new ApiResponse("JobPost deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
    }
}



