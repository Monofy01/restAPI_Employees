package com.brian.test.controller;

import com.brian.test.entity.Gender;
import com.brian.test.model.ModelGender;
import com.brian.test.service.GenderService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gender")
public class GenderController {

    @Autowired
    @Qualifier("genderService")
    GenderService genderService;

    @PutMapping("/addGender")
    public boolean AddGender(@RequestBody @Validated Gender gender) {
        System.out.println("QUE VALORES SALEN------> " + gender.getId_gender() + " " + gender.getName());
        return genderService.Create(gender);
    }

    @GetMapping("/getGender")
    public List<ModelGender> ShowData() {
        return genderService.Display();
    }




}
