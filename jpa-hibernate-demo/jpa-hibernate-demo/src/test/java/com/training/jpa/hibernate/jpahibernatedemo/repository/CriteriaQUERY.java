package com.training.jpa.hibernate.jpahibernatedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.jpa.hibernate.jpahibernatedemo.JpaHibernateDemoApplication;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateDemoApplication.class)
public class CriteriaQUERY {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// Criteria Query needs 5 steps:
	/*
	 * 1. Use criteria builder to create Criteria Query returning the expected
	 * result object
	 * 2. Define roots for tables which are involved in the query
	 * 3. Define Predicates etc using criteria query 
	 * 4. Add predicates etc to
	 * the criteria query 
	 * 5. Build the TypedQuery using the entity manager and
	 * criteria query
	 */

	@Test
	public void basic_select() {

		// 1). Use criteria builder to create criteria query returning expected
		// result object

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder
				.createQuery(Course.class);

		// 2). Define roots for the tables which are involved in query
		Root<Course> root = criteriaQuery.from(Course.class);

		// 3. Define Predicates etc using criteria query
		// 4. Add predicates etc to the criteria query

		// Build Typerd Query
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(root));

		List<Course> resultList = query.getResultList();

		logger.info("Simple Criteria Query Result ===> {}", resultList);

	}

	@Test
	public void basic_select_with_whereAndLike() {

		// 1. Use criterial builder to create criteria query returning expected
		// result object

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder
				.createQuery(Course.class);

		// 2. Define roots for the table which are used/involved in query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		// 3. Define predicates
		Predicate like100Steps = criteriaBuilder.like(courseRoot.get("name"),
				"%100 steps");

		// 4. Add Predicates to the criteria query
		criteriaQuery.where(like100Steps);

		// 5. Build Typed Query
		TypedQuery<Course> query = em.createQuery(criteriaQuery
				.select(courseRoot));

		List<Course> resultList = query.getResultList();

		logger.info("Simple Criteria Query Result ===> {}", resultList);

	}
	
	
	@Test
	public void basic_courses_without_students(){
		
		//1. Use criteria builder to create criteria query returning expected results object
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		//2. Define root tables which are involved into query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);		

		//3. Define predicate using criteria builder
		Predicate isEmptyStudentPredicate = criteriaBuilder.isEmpty(courseRoot.get("students"));
		
		//4. Add predictes to the criteria query
		criteriaQuery.where(isEmptyStudentPredicate);
		
		//5. Defined Typed Query
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		
		logger.info("isEMPTY STUDENT COURSES ==> {}", resultList);
			
		
	}
	
	
	@Test
	public void join(){
		
		// Use Criteria Builder to create criteria query returning expected result object
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		
		// Define roots
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		
		// Define Predicate
		Join<Object, Object> join = courseRoot.join("students");
		
		// Add Predicate
	
		//5. Defined Typed Query
				TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
				
				List<Course> resultList = query.getResultList();
				
				logger.info("JOINED COURSES ==> {}", resultList);
					
	}
	
	@Test
	public void leftJoin(){
		
		//Use QueryBuilder to create criteria query returning expected result object
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		//Define Roots
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		
		//Define Predicates
		Join<Object, Object> leftJoin = courseRoot.join("students",JoinType.LEFT);
		
		//Add predicates
		
		//Define TypedQuery
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		
		logger.info("LEFT JOINED COURSES ==> {}", resultList);
		
	}

}
