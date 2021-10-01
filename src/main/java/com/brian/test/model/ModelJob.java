package com.brian.test.model;

import com.brian.test.entity.Employee;
import com.brian.test.entity.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ModelJob {

    //ATTRIBUTES
    private long id_job;
    private String name;
    private double salary;
    @JsonIgnore
    private List<Employee> job;

    //CONSTRUCTORS
    public ModelJob(long id_job, String name, double salary, List<Employee> job) {
        this.id_job = id_job;
        this.name = name;
        this.salary = salary;
        this.job = job;
    }

    public ModelJob(Job job) {
        this.id_job = job.getId_job();
        this.name = job.getName();
        this.salary = job.getSalary();
        this.job = job.getJobList();
    }

    public ModelJob(long id_job) {
        this.id_job = id_job;
    }

    //GETTERS AND SETTERS


    public long getId_job() {
        return id_job;
    }

    public void setId_job(long id_job) {
        this.id_job = id_job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Employee> getJob() {
        return job;
    }

    public void setJob(List<Employee> job) {
        this.job = job;
    }
}
