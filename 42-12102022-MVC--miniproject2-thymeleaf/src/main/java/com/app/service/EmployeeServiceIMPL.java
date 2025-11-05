package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.errors.EmployeeNotFoundExeption;
import com.app.model.Employee;
import com.app.repository.IEmployeeRepository;

@Service("empService")
public class EmployeeServiceIMPL implements IEmployeeService {
	@Autowired
	private IEmployeeRepository repo;

	@Override
	public Iterable<Employee> getALLEmployee() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public String saveEmployeeList(Iterable<Employee> l) {
		return "data saved successfully " + repo.saveAll(l);
	}

	@Override
	public String saveEmployee(Employee e) {
		return "--" + repo.save(e).getEno();
	}

	@Override
	public Employee getEmployeeByNo(int eno) throws EmployeeNotFoundExeption {
		Employee emp = repo.findById(eno).orElseThrow(() -> new EmployeeNotFoundExeption("no record found by the id"));
		return emp;
	}

	@Override
	public void deleteEmployyeByNo(int eno) {
		repo.deleteById(eno);
	}

}
