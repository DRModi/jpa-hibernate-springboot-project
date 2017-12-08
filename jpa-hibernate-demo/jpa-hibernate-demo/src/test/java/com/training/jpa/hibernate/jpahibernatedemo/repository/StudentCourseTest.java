package com.training.jpa.hibernate.jpahibernatedemo.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.jpa.hibernate.jpahibernatedemo.JpaHibernateDemoApplication;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Course;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateDemoApplication.class)
public class StudentCourseTest {

	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	@Transactional
	public void retriveCoursesForStudent() {
		
		Student student = em.find(Student.class, 2001L);
		List<Course> courses = student.getCourses();
		
		logger.info("Student Details For 2001 ==> {}", student);
		logger.info("Course Details For 20001 ==> {}", courses);
		
		assertEquals("Anand",student.getName());
	}
	
	
	@Test
	@Transactional
	public void retriveStudentsForCourse() {
		Course course = em.find(Course.class, 1001L);
		List<Student> students = course.getStudents();
		logger.info("Course Details For 1001 ==> {}", course);
		logger.info("Student Details For 10001 ==> {}", students);
		
		assertEquals("JPA Hibernate in 100 steps",course.getName());
	}

}
