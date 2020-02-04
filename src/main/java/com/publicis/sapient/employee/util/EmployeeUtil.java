package com.publicis.sapient.employee.util;

import com.publicis.sapient.employee.model.Employee;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeUtil {
    public List<Employee> readEmployeeFromCSV(InputStream inputReader) {
        List<Employee> employeesList;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputReader));
            employeesList = br.lines().map(a -> {
                String s[] = a.split("\\|");
                return new Employee(s[0], s[1], s[2], Float.valueOf(s[3]));
            }).collect(Collectors.toList());

        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return employeesList;
    }
}
