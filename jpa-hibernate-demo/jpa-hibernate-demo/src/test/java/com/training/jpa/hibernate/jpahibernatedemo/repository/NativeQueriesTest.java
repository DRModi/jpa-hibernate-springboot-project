package com.training.jpa.hibernate.jpahibernatedemo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.training.jpa.hibernate.jpahibernatedemo.JpaHibernateDemoApplication;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateDemoApplication.class)
public class NativeQueriesTest {

	// @Autowired
	// CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void nativeQuery_BasicTest() {

		Query selectQuery = em.createNativeQuery("select * from course",
				Course.class);
		List<Course> courseList = selectQuery.getResultList();

		// List courseList =
		// em.createQuery("select c from course c").getResultList();
		logger.info("Selet c from Course c --> Query ==> {}", courseList);
		assertNotNull(courseList);
	}

	@Test
	public void nativeQuery_where() {
		Query selectWhereQuery = em.createNativeQuery(
				"select * from course where name like '%50%'", Course.class);
		List<Course> courseList = selectWhereQuery.getResultList();
		logger.info(
				"Course Name Like %50% ==> Number of course {}, course details {}",
				courseList.size(), courseList);
		assertNotNull(courseList);

	}

	@Test
	public void selectByIdWithNwithOutParameter() {
		Query selectQueryWithPosition = em.createNativeQuery(
				"select * from course where id = ?", Course.class);
		selectQueryWithPosition.setParameter(1, 1001L);
		Course coursewithPosition = (Course) selectQueryWithPosition
				.getSingleResult();
		logger.info("Course Name with Position ? ==> {} ", coursewithPosition);
		assertNotNull(coursewithPosition);

		Query selectQueryWithParameter = em.createNativeQuery(
				"select * from course where id = :id", Course.class);
		selectQueryWithParameter.setParameter("id", 1001L);
		Course coursewithParameter = (Course) selectQueryWithParameter
				.getSingleResult();
		logger.info("Course Name with Parameter :id ==> {} ", coursewithParameter);
		assertNotNull(coursewithParameter);

	}

	@Test
	@Transactional
	public void nativeQuery_bulkUpdate() {

		Query bulkLastUpdateTime = em
				.createNativeQuery("Update Course set last_updated_date = sysdate()");

		int updatedRecords = bulkLastUpdateTime.executeUpdate();

		logger.info("Total Updated Record as part of Bulk Update is ==> {}",
				updatedRecords);
		
		assertEquals(6, updatedRecords);

	}

}
