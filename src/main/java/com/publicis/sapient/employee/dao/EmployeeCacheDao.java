package com.publicis.sapient.employee.dao;

import com.publicis.sapient.employee.exception.NoRecordFoundException;
import com.publicis.sapient.employee.model.Employee;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface EmployeeCacheDao {
	List<Employee> getAllEmployee(InputStream inputReader);

	Employee saveEmployee(Employee employee);

	Map<String, Employee> getAllEmployees() throws NoRecordFoundException;

	void updateEmployeeList(Employee employee);

	Employee getEmployeeById(String empId) throws NoRecordFoundException;
}
