package com.publicis.sapient.employee.service;

import java.util.List;

import com.publicis.sapient.employee.exception.NoRecordFoundException;
import com.publicis.sapient.employee.model.Employee;

public interface EmployeeService {
    Employee getEmployee(String empId) throws NoRecordFoundException;

    List<Employee> updateEmpSalaryBasedOnPlace(String place, String percentage) throws NoRecordFoundException;

    List<Employee> getAllEmployee() throws NoRecordFoundException;
}
