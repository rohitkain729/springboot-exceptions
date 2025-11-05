package com.app.service;

import com.app.errors.EmployeeNotFoundExeption;
import com.app.model.Employee;

public interface IEmployeeService {

	public Iterable<Employee> getALLEmployee();

	public String saveEmployeeList(Iterable<Employee> l);

	public String saveEmployee(Employee e);

	public Employee getEmployeeByNo(int eno) throws EmployeeNotFoundExeption;

	public void deleteEmployyeByNo(int eno);
}
