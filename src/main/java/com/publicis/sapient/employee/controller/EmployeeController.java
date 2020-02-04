package com.publicis.sapient.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publicis.sapient.employee.exception.NoRecordFoundException;
import com.publicis.sapient.employee.model.Employee;
import com.publicis.sapient.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Employee Catalog", description  = "Employee Management System")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@ApiOperation(value = "Upgrade Employee Salary by Place", notes = "Upgrade Employees salary based on Place..")
	@RequestMapping(value = "/employee/place/{place}/salary/{percentage}", method = RequestMethod.PUT)
	public List<Employee> updateEmpSalary(@PathVariable String place, @PathVariable String percentage)
			throws NoRecordFoundException {
		return employeeService.updateEmpSalaryBasedOnPlace(place, percentage);
	}

	@ApiOperation(value = "List all employees", notes = "List All employees..")
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<Employee> getEmployeeList() throws NoRecordFoundException {
		return employeeService.getAllEmployee();
	}

	@ApiOperation(value = "Employee Information", notes = "Display Employee Information")
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable String id) throws NoRecordFoundException {
		return employeeService.getEmployee(id);
	}

}
