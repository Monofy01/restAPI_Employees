package com.brian.test.converter;

import com.brian.test.entity.Employee;
import com.brian.test.entity.Gender;
import com.brian.test.model.ModelEmployee;
import com.brian.test.model.ModelGender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("employeeConverter")
public class EmployeeConverter {

    public List<ModelEmployee> convertList(List<Employee> employeeList) {
        List<ModelEmployee> modelEmployeeList = new ArrayList<>();

        for (Employee employee : employeeList) {
            modelEmployeeList.add(new ModelEmployee(employee));
        }

        return modelEmployeeList;
    }
}
