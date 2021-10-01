package com.brian.test.service;

import com.brian.test.converter.JobConverter;
import com.brian.test.entity.Job;
import com.brian.test.model.ModelJob;
import com.brian.test.repository.RepositoryJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobService")
public class JobService {

    @Autowired
    @Qualifier("repositoryJob")
    private RepositoryJob repositoryJob;

    @Autowired
    @Qualifier("jobConverter")
    private JobConverter jobConverter;

    public boolean Create(Job job) {
        try {
            repositoryJob.save(job);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ModelJob> Display() {
        return jobConverter.convertList(repositoryJob.findAll());
    }


}
