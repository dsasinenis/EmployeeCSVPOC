package com.publicis.sapient.employee.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.publicis.sapient.employee.exception.NoRecordFoundException;
import com.publicis.sapient.employee.model.Employee;
import com.publicis.sapient.employee.util.EmployeeUtil;

@ExtendWith(SpringExtension.class)
public class EmployeeDaoTest {

	@Mock
	EmployeeUtil employeeUtil;
	@Mock
	InputStream inputStream;
	@Mock
	RedisTemplate<String, Employee> redisTemplate;
	@Mock
	HashOperations hashOperations;

	EmployeeCacheDaoImpl employeeCacheDao;

	@BeforeEach
	public void initEach() {
		when(redisTemplate.opsForHash()).thenReturn(hashOperations);
		doNothing().when(hashOperations).put(any(), any(), any());
		employeeCacheDao = new EmployeeCacheDaoImpl(redisTemplate);
		employeeCacheDao.employeeUtil = employeeUtil;
	}

	@Test
	void getAllEmployeeTest() throws NoRecordFoundException {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee("151081", "Suresh", "Bengalore", 1000));
		empList.add(new Employee("151082", "Ramesh", "Chennai", 2000));
		empList.add(new Employee("151083", "Mahesh", "Hyderabad", 3000));

		when(employeeUtil.readEmployeeFromCSV(inputStream)).thenReturn(empList);
		Assertions.assertEquals(empList, employeeCacheDao.getAllEmployee(inputStream));
	}

	@Test
	void saveEmployeeTest() {
		Employee emp = new Employee("151081", "Suresh", "Bengaluru", 1000);
		Assertions.assertEquals(emp,
				employeeCacheDao.saveEmployee(new Employee("151081", "Suresh", "Bengaluru", 1000)));
	}
}
