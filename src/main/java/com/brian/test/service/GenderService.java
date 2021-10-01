package com.brian.test.service;


import com.brian.test.converter.GenderConverter;
import com.brian.test.entity.Gender;
import com.brian.test.model.ModelGender;
import com.brian.test.repository.RepositoryGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("genderService")
public class GenderService {

    @Autowired
    @Qualifier("repositoryGender")
    private RepositoryGender repositoryGender;

    @Autowired
    @Qualifier("genderConverter")
    private GenderConverter genderConverter;

    public boolean Create(Gender gender) {
        try {
            repositoryGender.save(gender);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ModelGender> Display() {
        return genderConverter.convertList(repositoryGender.findAll( ));
    }



}
