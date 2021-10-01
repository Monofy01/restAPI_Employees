package com.brian.test.model;

import com.brian.test.entity.Employee;
import com.brian.test.entity.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ModelGender {

    //ATTRIBUTES
    private long id;
    private String name;
    @JsonIgnore
    private List<Employee> gender;


    //CONSTRUCTORS
    public ModelGender(long id, String name, List<Employee> gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public ModelGender(Gender gender) {
        this.id = gender.getId_gender();
        this.name = gender.getName();
        this.gender = gender.getGenderList();
    }

    public ModelGender(long id) {
        this.id = id;
    }

    //GETTERS AND SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getGender() {
        return gender;
    }

    public void setGender(List<Employee> gender) {
        this.gender = gender;
    }
}
