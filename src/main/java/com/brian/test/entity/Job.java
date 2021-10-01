package com.brian.test.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "JOBS")
public class Job implements Serializable {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JOB")
    private long id_job;

    @Column(name = "_NAME", columnDefinition="VARCHAR(255)")
    private String name;

    @Column(name = "SALARY", columnDefinition="DECIMAL(9,2)")
    private double salary;




    @JsonIgnore
    @OneToMany(targetEntity=Employee.class, mappedBy= "jobObject",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> jobList;

    //CONSTRUCTORS
    public Job(long id_job, String name, double salary, List<Employee> job) {
        this.id_job = id_job;
        this.name = name;
        this.salary = salary;
        this.jobList = job;
    }

    public Job() {
    }

    public Job(long id_job) {
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

    //-----------------------
    public List<Employee> getJobList() {
        return jobList;
    }

    public void setJobList(List<Employee> jobList) {
        this.jobList = jobList;
    }
}
