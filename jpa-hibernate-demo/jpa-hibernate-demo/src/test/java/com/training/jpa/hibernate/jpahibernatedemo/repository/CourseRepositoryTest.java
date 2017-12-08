package com.training.jpa.hibernate.jpahibernatedemo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void findById_BasicTest() {
		Course course = courseRepository.findById(1004L);
		//assertEquals("JPA Hibernate in 100 steps", course.getName());
		assertEquals("Delete Me", course.getName());
		
	}
	
	@Test
	@DirtiesContext
	public void deleteById_BasicTest(){
		courseRepository.deleteById(1002L);
		Course course = courseRepository.findById(1002L);
		assertNull(course);
		
	}
	
	@Test
	@DirtiesContext
	public void save_Basic(){
		
		Course course = courseRepository.findById(1001L);
		assertEquals("JPA Hibernate in 100 steps", course.getName());
		
		course.setName("JPA-Hibernate in 100 steps");
		
		courseRepository.save(course);
		
		Course updatedCourse = courseRepository.findById(1001L);
		assertEquals("JPA-Hibernate in 100 steps", updatedCourse.getName());
	}
	

}
