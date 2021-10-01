package com.brian.test.repository;


import com.brian.test.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

//QUERYS

@Repository("repositoryGender")
public interface RepositoryGender extends JpaRepository<Gender, Serializable> {


    public abstract List<Gender> findByName(String name);

}
