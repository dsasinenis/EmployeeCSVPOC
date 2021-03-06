package com.publicis.sapient.employee.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.publicis.sapient.employee.dao.EmployeeCacheDao;
import com.publicis.sapient.employee.exception.NoRecordFoundException;
import com.publicis.sapient.employee.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	EmployeeCacheDao employeeCacheDao;

	@PostConstruct
	public void init() throws IOException, NoRecordFoundException {
		Resource resource = resourceLoader.getResource("classpath:" + "Employee.csv");
		employeeCacheDao.getAllEmployee(resource.getInputStream());
	}

	@Override
	public ArrayList<Employee> getAllEmployee() throws NoRecordFoundException {
		return new ArrayList<Employee>(employeeCacheDao.getAllEmployees().values());
	}

	@Override
	public Employee getEmployee(final String empId) throws NoRecordFoundException {
		return employeeCacheDao.getEmployeeById(empId);
	}
	
	@Override
	public List<Employee> updateEmpSalaryBasedOnPlace(final String place, final String percentage)
			throws NoRecordFoundException {
		ArrayList<Employee> employeeList = this.getAllEmployee();
		float perct = Float.valueOf(percentage) / 100;

		List<Employee> updatedList = employeeList.stream()
				.map(f -> new Employee(f.getEmpId(), f.getName(), f.getPlace(),
						f.getPlace().equalsIgnoreCase(place) ? (f.getSalary() + f.getSalary() * perct) : f.getSalary()))
				.collect(Collectors.toList());

		if(0L ==  updatedList.stream().filter(f -> f.getPlace().equalsIgnoreCase(place)).count()) {
			throw new NoRecordFoundException();
		}
		
		updatedList.stream().filter(f -> f.getPlace().equalsIgnoreCase(place))
				.forEach(a -> employeeCacheDao.updateEmployeeList(a));
		return updatedList;
	}

}
