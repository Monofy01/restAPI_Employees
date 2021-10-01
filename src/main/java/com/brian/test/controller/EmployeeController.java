package com.brian.test.controller;


import com.brian.test.entity.Employee;
import com.brian.test.model.ModelEmployee;
import com.brian.test.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("employeeService")
    EmployeeService employeeService;

    @PutMapping("/addEmployee")
    public Map<String, Object> AddEmployee(@RequestBody @Validated Employee employee) {
        return employeeService.Create(employee);
    }

    @GetMapping("/getEmployee")
    public List<ModelEmployee> ShowData() {
        return employeeService.Display();
    }

    @GetMapping("/getEmployeeByJobId")
    public Map<String, Object> FindJobById(@RequestParam int id) {
        return employeeService.FindByJobId(id);
    }

    @GetMapping("/getEmployeeSalaryById")
    public Map<String, Object> TotalSalaryPerEmployee(@RequestParam int id, @RequestParam String start_date, @RequestParam String end_date) {
        return employeeService.TotalSalaryPerEmployee(id, start_date, end_date);
    }


}
