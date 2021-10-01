package com.brian.test.service;

import com.brian.test.converter.EmployeeConverter;
import com.brian.test.entity.Employee;
import com.brian.test.model.ModelEmployee;
import com.brian.test.model.ModelEmployeeWorkedHours;
import com.brian.test.model.ModelGender;
import com.brian.test.model.ModelJob;
import com.brian.test.repository.RepositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("employeeService")
public class EmployeeService {

    @Autowired
    @Qualifier("repositoryEmployee")
    private RepositoryEmployee repositoryEmployee;

    @Autowired
    @Qualifier("employeeConverter")
    private EmployeeConverter employeeConverter;

    //SERVICES FOR VALITATION
    @Autowired
    @Qualifier("genderService")
    private GenderService genderService;

    @Autowired
    @Qualifier("jobService")
    private JobService jobService;

    @Autowired
    @Qualifier("employeeWorkedHoursService")
    private EmployeeWorkedHoursService employeeWorkedHoursService;



    public Map<String, Object> Create(Employee employee) {
        Map<String, Object> responseRequest = new HashMap<>();
        List<String> errors = new ArrayList<>();
        try {

            List<ModelEmployee> dataNow = Display();
            List<ModelGender> gendersNow = genderService.Display();
            List<ModelJob> jobsNow = jobService.Display();

            //LISTS WITH IDS
            List<Long> idJobsNow = new ArrayList<>();
            List<Long> idGendersNow = new ArrayList<>();

            boolean flag = true;

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(employee.getBirthday());


            //ITERATE FOR EMPLOYEE FIELDS
            for (ModelEmployee modelEmployee: dataNow) {
                //VALIDATE NAME
                if (modelEmployee.getName().equals(employee.getName())) {
                    errors.add("Name is already defined");
                    //System.out.println("YA EXISTE EL NOMBRE");
                    flag = false;
                }
                //VALIDATE LAST_NAME
                if (modelEmployee.getLast_name().equals(employee.getLast_name())) {
                    errors.add("Last Name is already defined");
                    //System.out.println("YA EXISTE EL APELLIDO");
                    flag = false;
                }
                //VALIDATE AGE
                if ( (LocalDate.now().getYear() - calendar.get(Calendar.YEAR)) < 18) {
                    errors.add("Age is under 18");
                    //System.out.println("ES MENOR DE EDAD");
                    flag = false;
                }
            }


            //ITERATE FOR FIELDS EMPLOYEE GENDERS
            for (ModelGender modelGender : gendersNow) {
                idGendersNow.add(modelGender.getId());
            }
            //VALIDATE ID
            if (!idGendersNow.contains(employee.getGenderObject().getId_gender())) {
                errors.add("Gender doesn't exist");
                flag = false;
                //System.out.println("NO EXISTE EL ID DEL GENERO");
            }



            //ITERATE FOR FIELDS EMPLOYEE JOBS
            for (ModelJob modelJob : jobsNow) {
                idJobsNow.add(modelJob.getId_job());
            }
            //VALIDATE ID
            if (!idJobsNow.contains(employee.getJobObject().getId_job())) {
                errors.add("Job doesn't exist");
                flag = false;
                //System.out.println("TU TRABAJO NO EXISTE");
            }


            if (flag) {
                repositoryEmployee.save(employee);
                responseRequest.put("id", employee.getId());
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

    public List<ModelEmployee> Display() {
        return employeeConverter.convertList(repositoryEmployee.findAll());
    }

    public Map<String, Object> FindByJobId(int id) {

        Map<String, Object> responseRequest = new HashMap<>();
        List<String> errors = new ArrayList<>();

        List<ModelJob> jobsNow = jobService.Display();

        //LISTS WITH IDS
        List<Long> idJobsNow = new ArrayList<>();

        boolean flag = true;


        //ITERATE FOR FIELDS EMPLOYEE JOBS
        for (ModelJob modelJob : jobsNow) {
            idJobsNow.add(modelJob.getId_job());
        }
        //VALIDATE ID
        if (!idJobsNow.contains( (long ) id)) {
            errors.add("Job doesn't exist");
            flag = false;
            //System.out.println("TU TRABAJO NO EXISTE");
        }

        if (flag) {
            responseRequest.put("employees", employeeConverter.convertList(repositoryEmployee.Search(id)));
            responseRequest.put("success", true);
            return responseRequest;
        } else {
            responseRequest.put("employees", errors);
            responseRequest.put("success", false);
            return responseRequest;
        }

    }

    public Map<String, Object> TotalSalaryPerEmployee(int id, String start_date, String end_date) {
        Map<String, Object> responseRequest = new HashMap<>();
        List<String> errors = new ArrayList<>();

        List<ModelEmployee> employeesNow = Display();
        List<ModelEmployeeWorkedHours> employeeWorkedHoursNow = employeeWorkedHoursService.Display();


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
            responseRequest.put("payment", repositoryEmployee.TotalSalaryPerEmployee(id, start_date, end_date));
            responseRequest.put("success", true);
            return responseRequest;
        } else {
            responseRequest.put("payment", errors);
            responseRequest.put("success", false);
            return responseRequest;
        }
    }














}
