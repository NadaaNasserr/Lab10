package com.example.lab110.Service;

import com.example.lab110.Model.JobPost;
import com.example.lab110.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public List<JobPost> getPosts(){
        return jobPostRepository.findAll();
    }

    public void addPost(JobPost post){
        post.setPostingDate(LocalDate.now());
        jobPostRepository.save(post);
    }

    public Boolean updatePost(Integer postId, JobPost post){
        JobPost post1=jobPostRepository.getById(postId);
        if (post1==null){
            return false;
        }
        post1.setTitle(post.getTitle());
        post1.setDescription(post.getDescription());
        post1.setLocation(post.getLocation());
        post1.setSalary(post.getSalary());
        post1.setPostingDate(post.getPostingDate());
        jobPostRepository.save(post1);
        return true;
    }

    public Boolean deletePost(Integer postId){
        JobPost post1=jobPostRepository.getById(postId);
        if (post1==null){
            return false;
        }
        jobPostRepository.delete(post1);
        return true;
    }
}

