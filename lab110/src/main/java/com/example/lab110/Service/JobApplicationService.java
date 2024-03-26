package com.example.lab110.Service;

import com.example.lab110.Model.JobApplication;
import com.example.lab110.Model.JobPost;
import com.example.lab110.Model.User;
import com.example.lab110.Repository.JobApplicationRepository;
import com.example.lab110.Repository.JobPostRepository;
import com.example.lab110.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    public List<JobApplication> getApplications(){
        return jobApplicationRepository.findAll();
    }

    public Integer applyForJob(JobApplication application){
        if (jobPostRepository.existsById(application.getJobPostId())&& userRepository.existsById(application.getUserId())) {
            jobApplicationRepository.save(application);
            return 1;
        }
        if (!jobPostRepository.existsById(application.getUserId())){
            return 2;
        }
        return 3;
    }

    public Boolean updateApplications(Integer postId, JobApplication application){
        JobApplication application1=jobApplicationRepository.getById(postId);
        if (application1==null){
            return false;
        }
        application1.setJobPostId(application.getJobPostId());
        application1.setUserId(application.getUserId());
        jobApplicationRepository.save(application);
        return true;
    }
    public Boolean withdrawJobApplication(Integer postId){
        JobApplication application1=jobApplicationRepository.getById(postId);
        if (application1==null){
            return false;
        }
        jobApplicationRepository.delete(application1);
        return true;
    }
}
