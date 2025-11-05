package com.app.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Employee;
import com.app.service.IEmployeeService;

//@Component
public class EmployeeRunnerTest implements CommandLineRunner {

	@Autowired
	private IEmployeeService service;

	@Override
	public void run(String... args) throws Exception {

//Employee(Integer eno, String ename, String job, Double sal, Integer deptno)

		Employee e1 = new Employee(null, "priya", "bank", 100000.00, 12);
		Employee e2 = new Employee(null, "abhishek", "bank", 100000.00, 22);
		Employee e3 = new Employee(null, "ritu", "FCI", 90000.00, 32);

		String result = service.saveEmployeeList(List.of(e1, e2, e3));

		System.out.println(result);
	}

}
