package com.example.lab10.Service;


import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class JobPostService {


    private final JobPostRepository jobPostRepository;


    public List<JobPost> getAllJobPost(){
        return jobPostRepository.findAll();

    }


    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }



    public Boolean updateJobPost(Integer id , JobPost jobPost){

        Boolean isUpdated = jobPostRepository.existsById(id);
        JobPost  oldJobPost = jobPostRepository.getById(id);
        if(isUpdated) {


            oldJobPost.setTitle(jobPost.getTitle());
            oldJobPost.setDescription(jobPost.getDescription());
            oldJobPost.setLocation(jobPost.getLocation());
            oldJobPost.setSalary(jobPost.getSalary());
            oldJobPost.setPostingDate(jobPost.getPostingDate());

            jobPostRepository.save(oldJobPost);
            return true;
        }
        return false;
    }
    public Boolean deleteJobPost(Integer id) {

        Boolean isDeleted = jobPostRepository.existsById(id);
        JobPost jobPost = jobPostRepository.getById(id);
        if (isDeleted) {
            jobPostRepository.delete(jobPost);
            return true;
        }
        return false;

    }


}

