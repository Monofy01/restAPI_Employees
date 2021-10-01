package com.brian.test.controller;


import com.brian.test.entity.Job;
import com.brian.test.model.ModelJob;
import com.brian.test.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    @Qualifier("jobService")
    JobService jobService;

    @PutMapping("/addJob")
    public boolean addJob(@RequestBody @Validated Job job) {
        return jobService.Create(job);
    }

    @GetMapping("/getJob")
    public List<ModelJob> ShowData() {
        return jobService.Display();
    }

}
