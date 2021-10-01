package com.brian.test.model;

import com.brian.test.entity.Employee;
import com.brian.test.entity.EmployeeWorkedHours;
import com.brian.test.entity.Gender;
import com.brian.test.entity.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.util.List;

public class ModelEmployee {

    //ATTRIBUTES

    private long id;
    private Gender genderObject;
    private Job jobObject;
    private String name;
    private String last_name;
    private Date birthday;
    @JsonIgnore
    private List<EmployeeWorkedHours> employeeWorkedHours;


    //CONSTRUCTORS


    public ModelEmployee(long id, Gender genderObject, Job jobObject, String name, String last_name, Date birthday, List<EmployeeWorkedHours> employeeWorkedHours) {
        this.id = id;
        this.genderObject = genderObject;
        this.jobObject = jobObject;
        this.name = name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.employeeWorkedHours = employeeWorkedHours;
    }

    public ModelEmployee(Employee employee) {
        this.id = employee.getId();
        this.genderObject = employee.getGenderObject();
        this.jobObject = employee.getJobObject();
        this.name = employee.getName();
        this.last_name = employee.getLast_name();
        this.birthday = employee.getBirthday();
        this.employeeWorkedHours = employee.getEmployeeWorkedHoursList();
    }

    public ModelEmployee(long id) {
        this.id = id;
    }

    //GETTERS AND SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Gender getGenderObject() {
        return genderObject;
    }

    public void setGenderObject(Gender genderObject) {
        this.genderObject = genderObject;
    }

    public Job getJobObject() {
        return jobObject;
    }

    public void setJobObject(Job jobObject) {
        this.jobObject = jobObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<EmployeeWorkedHours> getEmployeeWorkedHours() {
        return employeeWorkedHours;
    }

    public void setEmployeeWorkedHours(List<EmployeeWorkedHours> employeeWorkedHours) {
        this.employeeWorkedHours = employeeWorkedHours;
    }
}
