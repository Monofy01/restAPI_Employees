package com.brian.test.service;

import com.brian.test.converter.EmployeeWorkedHoursConverter;
import com.brian.test.entity.EmployeeWorkedHours;
import com.brian.test.model.ModelEmployee;
import com.brian.test.model.ModelEmployeeWorkedHours;
import com.brian.test.model.ModelGender;
import com.brian.test.repository.RepositoryEmployeeWorkedHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("employeeWorkedHoursService")
public class EmployeeWorkedHoursService {

    @Autowired
    @Qualifier("repositoryEmployeeWorkedHours")
    private RepositoryEmployeeWorkedHours repositoryEmployeeWorkedHours;

    @Autowired
    @Qualifier("employeeWorkedHoursConverter")
    private EmployeeWorkedHoursConverter employeeWorkedHoursConverter;

//    VALIDAR QUE EMPLEADO EXISTA
//    TOTAL DE HORAS TRABAJADAS <= 20
//    FECHA TRABAJO SEA MENOR O IGUAL A LA ACTUAL
//    1 EMPLEADO SOLO 1 REGISTRO DE HORAS POR DIA


    //SERVICE FOR VALITATION
    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    public Map<String, Object> Create(EmployeeWorkedHours employeeWorkedHours) {
        Map<String, Object> responseRequest = new HashMap<>();
        List<String> errors = new ArrayList<>();
        try {

            List<ModelEmployeeWorkedHours> employeeWorkedHoursNow = Display();
            List<ModelEmployee> employeesNow = employeeService.Display();

            boolean flag = true;

            //LISTS WITH IDS
            List<Long> idEmployeesNow = new ArrayList<>();


            //ADD 1 DAY
            Calendar c = Calendar.getInstance();
            c.setTime(employeeWorkedHours.getWorked_date());
            c.add(Calendar.DATE, 1);
            employeeWorkedHours.setWorked_date(new java.sql.Date(c.getTimeInMillis()));




            //ITERATE FOR EMPLOYEES IDS
            for (ModelEmployee modelEmployee : employeesNow) {
                idEmployeesNow.add(modelEmployee.getId());
            }
                //VALIDATE ID EMPLOYEE
                if (!idEmployeesNow.contains(employeeWorkedHours.getEmployeeObject().getId())) {
                    errors.add("ID Employee doesn't exist in table Employees");
                    flag = false;
                    //System.out.println("NO EXISTE EL ID DEL EMPLEADO");
                }

            //VALIDATE ONLY ONE EMPLOYEE REGISTER PER DAY
            for (ModelEmployeeWorkedHours modelEmployeeWorkedHours: employeeWorkedHoursNow) {
                //VALIDATE ID_EMPLOYEE AND WORKED_DATE
                if (modelEmployeeWorkedHours.getEmployeeObject().getId() == employeeWorkedHours.getEmployeeObject().getId()
                        && modelEmployeeWorkedHours.getWorked_date().toString().equals(employeeWorkedHours.getWorked_date().toString())) {
                    errors.add("Worked Hours for Employee has been registered today");
                    //System.out.println("YA SE AGREGO REGISTRO EN ESA FECHA");
                    flag = false;
                }
            }

            //VALIDATE TOTAL WORKED HOURS
            if (employeeWorkedHours.getWorked_hours() > 20 || employeeWorkedHours.getWorked_hours() < 0) {
                errors.add("Worked_hours is more than 20");
                //System.out.println("NO PUEDEN SER MAS DE 20 HORAS");
                flag = false;
            }

            //VALIDATE TOTAL WORKED_DATE
            if (employeeWorkedHours.getWorked_date().toLocalDate().isAfter(LocalDate.now())) {
                errors.add("Worked_Date is greater than today");
                //System.out.println("FECHA NO MAYOR AL DIA DE HOY");
                flag = false;
            }



            if (flag) {
                repositoryEmployeeWorkedHours.save(employeeWorkedHours);
                responseRequest.put("id", employeeWorkedHours.getId_worked_hours());
                responseRequest.put("success", true);
                return responseRequest;
            } else {
                responseRequest.put("id", errors);
                responseRequest.put("success", false);
                return responseRequest;
            }

        } catch (Exception e) {
            responseRequest.put("id", errors);
            responseRequest.put("success", false);
            return responseRequest;
        }
    }

    public List<ModelEmployeeWorkedHours> Display() {
        return employeeWorkedHoursConverter.convertList(repositoryEmployeeWorkedHours.findAll());
    }


//    VALIDAR QUE EMPLEADO EXISTA
//    FECHA INICIO < FECHA FIN

    public Map<String, Object> TotalHours(int id, String start_date, String end_date) {
        Map<String, Object> responseRequest = new HashMap<>();
        List<String> errors = new ArrayList<>();


        List<ModelEmployeeWorkedHours> employeeWorkedHoursNow = Display();
        List<ModelEmployee> employeesNow = employeeService.Display();

        boolean flag = true;


        //LISTS WITH IDS
        List<Long> idEmployeesNow = new ArrayList<>();

        //ITERATE FOR EMPLOYEES IDS
        for (ModelEmployee modelEmployee : employeesNow) {
            idEmployeesNow.add(modelEmployee.getId());
        }
            //VALIDATE ID EMPLOYEE
            if (!idEmployeesNow.contains((long) id)) {
                errors.add("ID Employee doesn't exist in table Employees");
                flag = false;
                //System.out.println("NO EXISTE EL ID DEL EMPLEADO");
            }

        //VALIDATE START_DATE < END_DATE
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(start_date,formatter);
        LocalDate endDate = LocalDate.parse(end_date,formatter);

        if (startDate.isAfter(endDate)) {
            errors.add("Start_Date is greater than End_Date");
            //System.out.println("FECHA NO MAYOR AL DIA DE HOY");
            flag = false;
        }


        if (flag) {
            responseRequest.put("total_worked_hours", repositoryEmployeeWorkedHours.Total_hours(id, start_date, end_date));
            responseRequest.put("success", true);
            return responseRequest;
        } else {
            responseRequest.put("total_worked_hours", errors);
            responseRequest.put("success", false);
            return responseRequest;
        }


    }

}
