package com.brian.test.repository;


import com.brian.test.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("repositoryJob")
public interface RepositoryJob extends JpaRepository<Job, Serializable> {

}
