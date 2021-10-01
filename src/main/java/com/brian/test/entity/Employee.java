package com.brian.test.entity;




import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    //GENDER ID
    @ManyToOne
    @JoinColumn(name = "GENDER_ID")
    private Gender genderObject;

    //JOB ID
    @ManyToOne
    @JoinColumn(name = "JOB_ID")
    private Job jobObject;

    @Column(name = "NAME", columnDefinition="VARCHAR(255)")
    private String name;

    @Column(name = "LAST_NAME", columnDefinition="VARCHAR(255)")
    private String last_name;

    @Column(name = "DATE", columnDefinition="DATE")
    private Date birthday;


    //EMPLOYEE ID
    @JsonIgnore
    @OneToMany(targetEntity = EmployeeWorkedHours.class, mappedBy = "employeeObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmployeeWorkedHours> employeeWorkedHoursList;


    //CONSTRUCTORS
    public Employee(long id, Gender gender, Job jobObject, String name, String last_name, Date birthday, List<EmployeeWorkedHours> employeeWorkedHours) {
        this.id = id;
        this.genderObject = gender;
        this.jobObject = jobObject;
        this.name = name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.employeeWorkedHoursList = employeeWorkedHours;
    }

    public Employee() {
    }

    public Employee(long id) {
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

    public List<EmployeeWorkedHours> getEmployeeWorkedHoursList() {
        return employeeWorkedHoursList;
    }

    public void setEmployeeWorkedHoursList(List<EmployeeWorkedHours> employeeWorkedHoursList) {
        this.employeeWorkedHoursList = employeeWorkedHoursList;
    }
}
