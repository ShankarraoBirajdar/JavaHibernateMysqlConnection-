package com.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


import com.hibernate.entities.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	public Session session;
	public EmployeeDaoImpl(Session session) {
		this.session=session;
	}

	public int insert(Employee employee) {
		int r = (Integer) session.save(employee);
		return r;
	}

	public Employee getStudent(int id) {
		Query<Employee> query = session.createQuery("from Employee where id=:x", Employee.class);
		query.setParameter("x",id);
		Employee employee = query.getSingleResult();
		return employee;
	}

	public List<Employee> getAllStudent() {
		Query<Employee> query = session.createQuery("from Employee", Employee.class);
		List<Employee> list = query.list();
		return list;
	}

	public int update(Employee employee) {
		Query query = session.createQuery("update Employee set age=:x, name=:y, salary=:z where id=:a");
		query.setParameter("x",employee.getAge());
		query.setParameter("y", employee.getName());
		query.setParameter("z", employee.getSalary());
		query.setParameter("a", employee.getId());
		int r= query.executeUpdate();
		return r;
	}

	public int delete(int id) {
		Query query = session.createQuery("delete from Employee where id=:x");
		query.setParameter("x",id);
		int r= query.executeUpdate();
		return r;
	}

}
