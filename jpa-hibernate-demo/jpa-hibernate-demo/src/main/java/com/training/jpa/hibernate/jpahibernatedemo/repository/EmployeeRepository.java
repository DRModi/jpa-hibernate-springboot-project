package com.training.jpa.hibernate.jpahibernatedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.jpa.hibernate.jpahibernatedemo.entity.Employee;
import com.training.jpa.hibernate.jpahibernatedemo.entity.FullTimeEmployee;
import com.training.jpa.hibernate.jpahibernatedemo.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	private EntityManager entityManager;

	public void insertEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	public List<Employee> retriveAllEmployees() {

		return entityManager.createQuery("select e from Employee e",
				Employee.class).getResultList();
	}

	public List<FullTimeEmployee> retriveAllFullTimeEmployee() {

		return entityManager.createQuery("select fte from FullTimeEmployee fte",
				FullTimeEmployee.class).getResultList();
	}
	
	public List<PartTimeEmployee> retriveAllPartTimeEmployee() {

		return entityManager.createQuery("select pte from PartTimeEmployee pte",
				PartTimeEmployee.class).getResultList();
	}
}
