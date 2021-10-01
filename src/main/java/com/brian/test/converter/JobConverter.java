package com.brian.test.converter;

import com.brian.test.entity.Gender;
import com.brian.test.entity.Job;
import com.brian.test.model.ModelGender;
import com.brian.test.model.ModelJob;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("jobConverter")
public class JobConverter {

    public List<ModelJob> convertList(List<Job> jobList) {
        List<ModelJob> modelJobList = new ArrayList<>();

        for (Job job : jobList) {
            modelJobList.add(new ModelJob(job));
        }

        return modelJobList;
    }
}
