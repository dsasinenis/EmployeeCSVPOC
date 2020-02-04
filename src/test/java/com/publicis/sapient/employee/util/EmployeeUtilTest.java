package com.publicis.sapient.employee.util;

import com.publicis.sapient.employee.model.Employee;
import com.publicis.sapient.employee.util.EmployeeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)

public class EmployeeUtilTest {
    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void readEmployeeFromCSVTest() throws IOException {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("151081", "Suresh", "Bengaluru", 1000));
        empList.add(new Employee("151082", "Ramesh", "Chennai", 2000));
        empList.add(new Employee("151083", "Mahesh", "Hyderabad", 3000));
        empList.add(new Employee("151084", "Srinivas", "Bengaluru", 4000));
        Resource resource = resourceLoader.getResource("classpath:" + "Test.csv");
        EmployeeUtil employeeUtil = new EmployeeUtil();
        Assertions.assertEquals(empList, employeeUtil.readEmployeeFromCSV(resource.getInputStream()));
    }

}
