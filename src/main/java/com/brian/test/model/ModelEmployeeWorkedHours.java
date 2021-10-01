package com.brian.test.model;

import com.brian.test.entity.Employee;
import com.brian.test.entity.EmployeeWorkedHours;

import java.util.Date;

public class ModelEmployeeWorkedHours {

    //ATTRIBUTES
    private long id_worked_hours;
    private Employee employeeObject;
    private double worked_hours;
    private Date worked_date;

    //CONSTRUCTORS


    public ModelEmployeeWorkedHours(long id_worked_hours, Employee employeeObject, double worked_hours, Date worked_date) {
        this.id_worked_hours = id_worked_hours;
        this.employeeObject = employeeObject;
        this.worked_hours = worked_hours;
        this.worked_date = worked_date;
    }

    public ModelEmployeeWorkedHours(EmployeeWorkedHours employeeWorkedHours) {
        this.id_worked_hours = employeeWorkedHours.getId_worked_hours();
        this.employeeObject = employeeWorkedHours.getEmployeeObject();
        this.worked_hours = employeeWorkedHours.getWorked_hours();
        this.worked_date = employeeWorkedHours.getWorked_date();
    }



    public ModelEmployeeWorkedHours(long id_worked_hours) {
        this.id_worked_hours = id_worked_hours;
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
