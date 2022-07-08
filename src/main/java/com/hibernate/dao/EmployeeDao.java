package com.hibernate.dao;

import java.util.List;

import com.hibernate.entities.Employee;

public interface EmployeeDao {
	
	public int insert(Employee employee);

	public Employee getStudent(int id);

	public List<Employee> getAllStudent();

	public int update(Employee employee);

	public int delete(int id);


}
