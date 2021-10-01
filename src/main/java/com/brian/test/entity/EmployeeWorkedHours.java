package com.brian.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "EMPLOYEE_WORKED_HOURS")
public class EmployeeWorkedHours implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_WORKED_HOURS")
    private long id_worked_hours;

    //EMPLOYEE ID
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employeeObject;

    @Column(name = "WORKED_HOURS")
    private double worked_hours;

    @Column(name = "WORKED_DATE", columnDefinition="DATE")
    private Date worked_date;


    //CONSTRUCTORS
    public EmployeeWorkedHours(long id_worked_hours, Employee employeeObject, double worked_hours, Date worked_date) {
        this.id_worked_hours = id_worked_hours;
        this.employeeObject = employeeObject;
        this.worked_hours = worked_hours;
        this.worked_date = worked_date;
    }

    public EmployeeWorkedHours() {
    }

    //GETTERS AND SETTERS
    public long getId_worked_hours() {
        return id_worked_hours;
    }

    public void setId_worked_hours(long id_worked_hours) {
        this.id_worked_hours = id_worked_hours;
    }

    public Employee getEmployeeObject() {
        return employeeObject;
    }

    public void setEmployeeObject(Employee employeeObject) {
        this.employeeObject = employeeObject;
    }

    public double getWorked_hours() {
        return worked_hours;
    }

    public void setWorked_hours(double worked_hours) {
        this.worked_hours = worked_hours;
    }

    public Date getWorked_date() {
        return worked_date;
    }

    public void setWorked_date(Date worked_date) {
        this.worked_date = worked_date;
    }
}
