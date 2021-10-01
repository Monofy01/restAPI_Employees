package com.brian.test.repository;

import com.brian.test.entity.Employee;
import com.brian.test.entity.EmployeeWorkedHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

@Repository("repositoryEmployeeWorkedHours")
public interface RepositoryEmployeeWorkedHours extends JpaRepository<EmployeeWorkedHours, Serializable> {

    @Query(
            value = "SELECT SUM(worked_hours) FROM employee_worked_hours WHERE employee_worked_hours.employee_id = :id AND employee_worked_hours.worked_date BETWEEN :start_date AND :end_date",
            nativeQuery = true
    )
    int Total_hours(@RequestParam int id, @RequestParam String start_date, @RequestParam String end_date);



}
