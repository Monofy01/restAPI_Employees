package com.brian.test.controller;

import com.brian.test.entity.Employee;
import com.brian.test.entity.EmployeeWorkedHours;
import com.brian.test.model.ModelEmployee;
import com.brian.test.model.ModelEmployeeWorkedHours;
import com.brian.test.service.EmployeeService;
import com.brian.test.service.EmployeeWorkedHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employeeWorkedHours")
public class EmployeeWorkedHoursController {

    @Autowired
    @Qualifier("employeeWorkedHoursService")
    EmployeeWorkedHoursService employeeWorkedHoursService;

    @PutMapping("/addEmployeeWorkedHours")
    public Map<String, Object> AddEmployee(@RequestBody @Validated EmployeeWorkedHours employeeWorkedHours) {
        return employeeWorkedHoursService.Create(employeeWorkedHours);
    }

    @GetMapping("/getEmployeeWorkedHours")
    public List<ModelEmployeeWorkedHours> ShowData() {
        return employeeWorkedHoursService.Display();
    }

    @GetMapping("/getTotalHours")
    public Map<String, Object> TotalHours(@RequestParam int id, @RequestParam String start_date, @RequestParam String end_date) {
        return employeeWorkedHoursService.TotalHours(id, start_date, end_date);
    }
}
