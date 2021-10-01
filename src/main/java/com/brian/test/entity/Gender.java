package com.brian.test.entity;





import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "GENDERS")
@Entity
public class Gender implements Serializable {


    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GENDER")
    private long id_gender;

    @Column(name = "_NAME", columnDefinition="VARCHAR(255)")
    private String name;


    @JsonIgnore
    @OneToMany(mappedBy = "genderObject", targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> genderList;

    //CONSTRUCTORS
    public Gender(long id_gender, String name, List<Employee> gender) {
        this.id_gender = id_gender;
        this.name = name;
        this.genderList = gender;
    }

    public Gender() {
    }

    public Gender(long id_gender) {
        this.id_gender = id_gender;
    }

    //GETTERS AND SETTERS
    public long getId_gender() {
        return id_gender;
    }

    public void setId_gender(long id_gender) {
        this.id_gender = id_gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //-------------------------
    public List<Employee> getGenderList() {
        return genderList;
    }

    public void setGenderList(List<Employee> gender) {
        this.genderList = gender;
    }
}
