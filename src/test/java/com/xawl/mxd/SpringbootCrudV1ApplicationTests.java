package com.xawl.mxd;

import com.xawl.mxd.bean.Employee;
import com.xawl.mxd.bean.EmployeeExample;
import com.xawl.mxd.dao.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootCrudV1ApplicationTests {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Test
	void contextLoads() {
		Employee employee = employeeMapper.selectByPrimaryKeyWithDept(500);
		System.out.println(employee);

//		List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
//		for (Employee employee1:employees){
//			System.out.println(employee1);
//		}

	}

}
