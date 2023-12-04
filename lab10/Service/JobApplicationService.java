package com.example.lab10.Service;


import com.example.lab10.Model.JobApplication;

import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {


    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;


    public List<JobApplication> getAllJobApplication() {
        return jobApplicationRepository.findAll();

    }

    public String addJobApplication(JobApplication jobApplication) {

        if (userRepository.existsById(jobApplication.getUserId())) {
            if (jobPostRepository.existsById(jobApplication.getJobPostId())) {
                jobApplicationRepository.save(jobApplication);
                return "OK";
            }
            return "job post id not found";
        }
        return "user id not found";
    }


    public Boolean WithdrawJobApplication(Integer id){

        Boolean isDeleted= jobApplicationRepository.existsById(id);
        JobApplication jobApplication = jobApplicationRepository.getById(id);
        if(isDeleted){
            jobApplicationRepository.delete(jobApplication);
            return true;
        }
       return false;
    }

        }

