package com.training.jpa.hibernate.jpahibernatedemo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.training.jpa.hibernate.jpahibernatedemo.entity.Course;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Passport;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	private EntityManager entityManger;

	public void insertStudentWithPassport() {
		Passport passport = new Passport("E3004P");
		entityManger.persist(passport);
		Student student = new Student("Vinay");
		student.setPassport(passport);
		entityManger.persist(student);
		// entityManger.flush();

	}

	public void insertStudentAndCourse_HC() {
		Student student = new Student("Ketan");
		Course course = new Course("Spring Cloud in 51 Steps");
		entityManger.persist(student);
		entityManger.persist(course);

		// As student is the owning side of the relationship - persisting student one more time
		// Add student id and course id, to relationship table Student_Course
		student.addCourse(course);
		course.addStudent(student);
		entityManger.persist(student);

	}
	
	public void insertStudentAndCourse(Student student, Course course){
		student.addCourse(course);
		course.addStudent(student);
		
		entityManger.persist(student);
		entityManger.persist(course);
	}

}
