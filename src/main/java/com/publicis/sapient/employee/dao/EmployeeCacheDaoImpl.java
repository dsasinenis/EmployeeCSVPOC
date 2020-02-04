package com.publicis.sapient.employee.dao;

import com.publicis.sapient.employee.exception.NoRecordFoundException;
import com.publicis.sapient.employee.model.Employee;
import com.publicis.sapient.employee.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeCacheDaoImpl implements EmployeeCacheDao {

	@Autowired
	EmployeeUtil employeeUtil;

	private HashOperations hashOperations;

	private RedisTemplate redisTemplate;

	public EmployeeCacheDaoImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	@Override
	public List<Employee> getAllEmployee(InputStream inputReader) {
		List<Employee> employeeList = employeeUtil.readEmployeeFromCSV(inputReader);
		employeeList.parallelStream().forEach(a -> saveEmployee(a));
		return employeeList;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		hashOperations.put("employee", employee.getEmpId(), employee);
		return employee;
	}

	@Override
	public Map<String, Employee> getAllEmployees() throws NoRecordFoundException {
		Map employeesMap = hashOperations.entries("employee");
		if (employeesMap.isEmpty())
			throw new NoRecordFoundException();
		return employeesMap;
	}

	@Override
	public void updateEmployeeList(Employee employee) {
		hashOperations.put("employee", employee.getEmpId(), employee);
	}

	@Override
	public Employee getEmployeeById(String empId) throws NoRecordFoundException {
		Employee employeeDetail = (Employee) hashOperations.get("employee", empId);
		if (employeeDetail == null)
			throw new NoRecordFoundException();
		return employeeDetail;
	}
}
