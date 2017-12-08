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
import com.training.jpa.hibernate.jpahibernatedemo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateDemoApplication.class)
public class CourseJPQLTest {

	// @Autowired
	// CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void JPQL_BasicTest() {

		Query selectQuery = em.createQuery("Select c From Course c");
		List courseList = selectQuery.getResultList();

		// List courseList =
		// em.createQuery("select c from course c").getResultList();
		logger.info("Selet c from Course c --> Query ==> {}", courseList);
		assertNotNull(courseList);
	}

	@Test
	public void JPQL_TypedTest() {

		TypedQuery<Course> typedQuery = em.createQuery(
				"Select c From Course c", Course.class);
		List<Course> courseList = typedQuery.getResultList();
		logger.info("Selet typed query ==> {}", courseList);
		assertNotNull(courseList);
	}

	@Test
	public void JPQL_whereTest() {

		TypedQuery<Course> createQuery = em.createQuery(
				"Select c From Course c where name like '%100%'", Course.class);
		List<Course> courseList = createQuery.getResultList();
		logger.info("Selet typed query like 100  ==> {}", courseList);
		assertNotNull(courseList);
	}

	@Test
	public void JPQL_Courses_Without_Students() {

		TypedQuery<Course> createQuery = em.createQuery(
				"Select c From Course c where c.students is empty",
				Course.class);
		List<Course> courseList = createQuery.getResultList();
		logger.info("Find out course where student is not asssign   ==> {}",
				courseList);
		assertNotNull(courseList);
	}

	@Test
	public void jpql_course_with_morethan2student() {

		TypedQuery<Course> createQuery = em.createQuery(
				"select c from Course c where size(c.students) >= 2",
				Course.class);
		List<Course> courseList = createQuery.getResultList();

		logger.info("Course with more than 2 students ==> {}", courseList);
		assertNotNull(courseList);
	}

	@Test
	public void jpql_course_with_orderby_students() {

		TypedQuery<Course> queryAscending = em.createQuery(
				"select c from Course c order by size(c.students)",
				Course.class);
		TypedQuery<Course> queryDescending = em.createQuery(
				"select c from Course c order by size(c.students) desc",
				Course.class);

		List<Course> resultListAsc = queryAscending.getResultList();
		List<Course> resultListDesc = queryDescending.getResultList();

		logger.info("Course with Order By students - ASC==> {}", resultListAsc);
		assertNotNull(resultListAsc);

		logger.info("Course with Order By students - DESC ==> {}",
				resultListDesc);
		assertNotNull(resultListDesc);

	}

	@Test
	public void jpql_student_with_matching_passportNumberPattern() {

		TypedQuery<Student> query = em.createQuery(
				"select s from Student s where s.passport.number like '%300%'",
				Student.class);
		List<Student> resultList = query.getResultList();

		logger.info("All Student with Passport number Like - 300 ==> {}",
				resultList);

		List<Student> query1Result = em
				.createQuery(
						"select s from Student s where s.passport.number between '3000' and '3004' ",
						Student.class).getResultList();
		// List<Student> query1Result =
		// em.createQuery("select s from Student s where s.passport.number like '%300%'",
		// Student.class).getResultList();

		logger.info("BETWEEN  ====> {}", query1Result);

		assertNotNull(resultList);

	}

	// Like other fucnctions...
	// Between 10 and 100
	// IS NULL
	// Uper, lower, trim, length

	// Join - it will match both record - means courses which are having
	// students
	// Left Join - Result includes all the course - without/with assigned
	// students
	// Cross Join - it will simply cross the tables - each row of one table with
	// each row of other table

	@Test
	public void join() {

		Query query = em
				.createQuery("select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();

		for (Object[] object : resultList) {
			logger.info(
					"Courses ==> {} and Associated Student Students ==> {}",
					object[0], object[1]);
		}
	}

	@Test
	public void left_join() {
		Query query = em
				.createQuery("select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();

		for (Object[] object : resultList) {
			logger.info(
					"Courses ==> {} and Associated Student Students ==> {}",
					object[0], object[1]);
		}
	}

	
	@Test
	public void cross_join(){
		Query query = em.createQuery("select c, s from Course c, Student s");
		
		List<Object []> resultList = query.getResultList();
		
		for (Object[] object : resultList) {
			logger.info("Courses ==> {} and Associated Student Students ==> {}", object[0], object[1]);	
		}
	}
	
}
