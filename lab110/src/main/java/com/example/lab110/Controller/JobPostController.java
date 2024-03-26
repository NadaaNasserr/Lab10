package com.example.lab110.Controller;
import com.example.lab110.ApiResponse.ApiResponse;

import com.example.lab110.Model.JobPost;
import com.example.lab110.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobPosts")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getPosts(){
        return ResponseEntity.ok(jobPostService.getPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid JobPost post, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        jobPostService.addPost(post);
        return ResponseEntity.ok().body(new ApiResponse("Post Added"));
    }

    @PutMapping("/update/{jpId}")
    public ResponseEntity updatePost(@PathVariable Integer jpId, @RequestBody @Valid JobPost post, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        Boolean isUpdated= jobPostService.updatePost(jpId,post);
        if (isUpdated){
            return ResponseEntity.ok().body(new ApiResponse("Post Updated"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Post ID not found"));

    }

    @DeleteMapping("/delete/{jpId}")
    public ResponseEntity deletePost(@PathVariable Integer jpId){
        Boolean isDeleted=jobPostService.deletePost(jpId);
        if (isDeleted){
            return ResponseEntity.ok().body(new ApiResponse("Post Deleted"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Post ID not found"));
    }
}