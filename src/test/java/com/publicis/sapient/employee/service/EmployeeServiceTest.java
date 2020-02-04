package com.publicis.sapient.employee.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.publicis.sapient.employee.dao.EmployeeCacheDaoImpl;
import com.publicis.sapient.employee.exception.NoRecordFoundException;
import com.publicis.sapient.employee.model.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class EmployeeServiceTest {
    @Mock
    EmployeeCacheDaoImpl employeeCacheDao;

    @InjectMocks
    @Spy
    EmployeeServiceImpl employeeService;
 
	@BeforeEach
    void init() throws NoRecordFoundException {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("151081", "Suresh", "Bengaluru", 1000));
        empList.add(new Employee("151082", "Ramesh", "Chennai", 2000));
        empList.add(new Employee("151083", "Mahesh", "Hyderabad", 3000));
        Mockito.doReturn(empList).when(employeeService).getAllEmployee();
        Mockito.doNothing().when(employeeCacheDao).updateEmployeeList(any());
    }

    @Test
    void updateEmpSalaryBasedOnPlaceTest() throws NoRecordFoundException {
        List<Employee> empList=new ArrayList<>();
        empList.add(new Employee("151081", "Suresh", "Bengaluru", 2000));
        empList.add(new Employee("151082", "Ramesh", "Chennai", 2000));
        empList.add(new Employee("151083", "Mahesh", "Hyderabad", 3000));
        Assertions.assertEquals(empList,employeeService.updateEmpSalaryBasedOnPlace("Bengaluru","100"));
    }
}