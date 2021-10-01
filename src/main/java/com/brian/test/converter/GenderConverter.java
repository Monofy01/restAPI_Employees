package com.brian.test.converter;

import com.brian.test.entity.Gender;
import com.brian.test.model.ModelGender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("genderConverter")
public class GenderConverter {

    public List<ModelGender> convertList(List<Gender> genderList) {
        List<ModelGender> modelGenderList = new ArrayList<>();

        for (Gender gender : genderList) {
            modelGenderList.add(new ModelGender(gender));
        }

        return modelGenderList;
    }
}
