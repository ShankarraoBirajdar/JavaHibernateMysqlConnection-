package com.hibernate.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import com.hibernate.dao.EmployeeDao;
import com.hibernate.dao.EmployeeDaoImpl;
import com.hibernate.entities.Employee;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Test {
	
	public static void main(String[] args) {
//		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
//		SessionFactory factory = meta.getSessionFactoryBuilder().build();

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		EmployeeDao employeeDao = new EmployeeDaoImpl(session);
		Transaction tx = session.beginTransaction();
		input(employeeDao);
		tx.commit();
		System.out.println("successfully saved");

		session.close();
		factory.close();

	}

	static void input(EmployeeDao employeeDao) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean go = true;
		while (go) {
			System.out.println("Press 1 for Add New Student");
			System.out.println("Press 2 for Display All Students");
			System.out.println("Press 3 for Get Details of Single Student");
			System.out.println("Press 4 for Update Student Details");
			System.out.println("Press 5 for Delete Student Details");
			System.out.println("Press 6 for Exit");
			try {
				int input = Integer.parseInt(br.readLine());
				switch (input) {

				case 1:
					System.out.println("================insertStudentDetails====================");
					insertStudentDetails(br, employeeDao);
					System.out.println("====================================");
					break;
				case 2:
					System.out.println("================printAllStudentDetails====================");
					printAllStudentDetails(employeeDao);
					System.out.println("====================================");
					break;
				case 3:
					System.out.println("================printStudentDetails====================");
					printStudentDetails(br, employeeDao);
					System.out.println("====================================");
					break;
				case 4:
					System.out.println("================updateStudentDetails====================");
					updateStudentDetails(br, employeeDao);
					System.out.println("====================================");
					break;
				case 5:
					System.out.println("=================deleteStudentDetails===================");
					deleteStudentDetails(br, employeeDao);
					System.out.println("====================================");
					break;
				case 6:
					go = false;
					break;

				}

			} catch (Exception e) {
				System.out.println("Invalid Input Please Try Again");
				System.out.println(e.getMessage());

			}
		}

		System.out.println("Thank You Using My Console Application");
		System.out.println("See you soon!!");

	}

	static void insertStudentDetails(BufferedReader br, EmployeeDao employeeDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter Employee Name");
		String sName = br.readLine();
		System.out.println("Enter Employee Age");
		int sAge = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Salary");
		int sSalary = Integer.parseInt(br.readLine());
		Employee employee = new Employee();
		employee.setName(sName);
		employee.setAge(sAge);
		employee.setSalary(sSalary);
		int i = employeeDao.insert(employee);
		if (i > 0)
			System.out.println(i + " Record inserted");
		else
			System.out.println("Record Not inserted");
	}

	static void updateStudentDetails(BufferedReader br, EmployeeDao employeeDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter Employee Id");
		int sId = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Name");
		String sName = br.readLine();
		System.out.println("Enter Employee Age");
		int sAge = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Salary");
		int sSalary = Integer.parseInt(br.readLine());
		Employee employee = new Employee();
		employee.setName(sName);
		employee.setAge(sAge);
		employee.setSalary(sSalary);
		int i = employeeDao.update(employee);
		if (i > 0)
			System.out.println(i + " Record Updated");
		else
			System.out.println("Record Not Updated");
	}

	static void deleteStudentDetails(BufferedReader br, EmployeeDao employeeDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter Employee Id");
		int sId = Integer.parseInt(br.readLine());
		int i = employeeDao.delete(sId);
		if (i > 0)
			System.out.println(i + " Record Deleted");
		else
			System.out.println("Record Not Deleted");
	}

	static void printStudentDetails(BufferedReader br, EmployeeDao employeeDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter Student Id");
		int sId = Integer.parseInt(br.readLine());
		Employee employee = employeeDao.getStudent(sId);
		System.out.println("Id: " + employee.getId());
		System.out.println("Name: " + employee.getName());
		System.out.println("Age: " + employee.getAge());
		System.out.println("Salary: " + employee.getSalary());

	}

	static void printAllStudentDetails(EmployeeDao employeeDao) throws NumberFormatException, IOException {

		List<Employee> employees = employeeDao.getAllStudent();
		for (Employee employee : employees) {
			System.out.println("Id: " + employee.getId());
			System.out.println("Name: " + employee.getName());
			System.out.println("Age: " + employee.getAge());
			System.out.println("Salary: " + employee.getSalary());
		}

	}



}
