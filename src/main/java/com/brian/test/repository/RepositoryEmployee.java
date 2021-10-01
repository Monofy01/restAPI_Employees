package com.brian.test.repository;


import com.brian.test.entity.Employee;
import com.brian.test.entity.Gender;
import com.brian.test.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

@Repository("repositoryEmployee")
public interface RepositoryEmployee extends JpaRepository<Employee, Serializable> {

    @Query(
            value = "SELECT * FROM employees WHERE employees.job_id = :id",
            nativeQuery = true
    )
    List<Employee> Search(@RequestParam int id);

    @Query(
            value = "SELECT CONVERT(decimal(9,2), SUM(b.SALARY))\n" +
                    "\tFROM EMPLOYEES AS a\n" +
                    "\tINNER JOIN JOBS AS b ON a.JOB_ID = b.ID_JOB\n" +
                    "\tINNER JOIN EMPLOYEE_WORKED_HOURS AS c ON a.ID = EMPLOYEE_ID\n" +
                    "\tWHERE c.WORKED_DATE BETWEEN :start_date AND :end_date AND a.ID = :id",
            nativeQuery = true
    )
    double TotalSalaryPerEmployee(@RequestParam int id, @RequestParam String start_date, @RequestParam String end_date);



}
