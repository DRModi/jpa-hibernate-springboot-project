package com.training.jpa.hibernate.jpahibernatedemo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.jpa.hibernate.jpahibernatedemo.JpaHibernateDemoApplication;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateDemoApplication.class)
public class CourseNamedQueriesTest {

	//@Autowired
	//CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void JPQL_BasicTest() {
		
		Query selectQuery = em.createNamedQuery("query_get_all_courses");
		List courseList = selectQuery.getResultList();
		
		//List courseList = em.createQuery("select c from course c").getResultList();
		logger.info("Selet c from Course c -->Named Query ==> {}",courseList);
		assertNotNull(courseList);
	}
	
	@Test
	public void JPQL_TypedTest(){
		
		TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> courseList = typedQuery.getResultList();
		logger.info("Selet typed query ==> {}",courseList);
		assertNotNull(courseList);
	}
	
	@Test
	public void JPQL_whereTest(){
		
		TypedQuery<Course> createQuery = em.createNamedQuery("query_get_NameLike100_courses", Course.class);
		List<Course> courseList = createQuery.getResultList();
		logger.info("Selet typed query like 100  ==> {}",courseList);
		assertNotNull(courseList);
	}

	

}
