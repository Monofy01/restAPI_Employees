package com.brian.test.converter;

import com.brian.test.entity.EmployeeWorkedHours;
import com.brian.test.model.ModelEmployeeWorkedHours;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("employeeWorkedHoursConverter")
public class EmployeeWorkedHoursConverter {

    public List<ModelEmployeeWorkedHours> convertList(List<EmployeeWorkedHours> employeeWorkedHoursList) {
        List<ModelEmployeeWorkedHours> modelEmployeeWorkedHoursList = new ArrayList<>();

        for (EmployeeWorkedHours employeeWorkedHours: employeeWorkedHoursList) {
            modelEmployeeWorkedHoursList.add(new ModelEmployeeWorkedHours(employeeWorkedHours));
        }

        return modelEmployeeWorkedHoursList;
    }
}
