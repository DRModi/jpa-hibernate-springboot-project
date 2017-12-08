package com.training.jpa.hibernate.jpahibernatedemo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.training.jpa.hibernate.jpahibernatedemo.entity.Course;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Employee;
import com.training.jpa.hibernate.jpahibernatedemo.entity.FullTimeEmployee;
import com.training.jpa.hibernate.jpahibernatedemo.entity.PartTimeEmployee;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Review;
import com.training.jpa.hibernate.jpahibernatedemo.entity.Student;
import com.training.jpa.hibernate.jpahibernatedemo.repository.CourseRepository;
import com.training.jpa.hibernate.jpahibernatedemo.repository.EmployeeRepository;
import com.training.jpa.hibernate.jpahibernatedemo.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateDemoApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("========= CommanLine Runner - START  ============");
		//Course course = courseRepository.findById(1001L);
		//logger.info("Course 1001 ==> {}", course);
		
		//courseRepository.entityManagerMethodsTesting();
		
		//logger.info("Delete By ID = 1001");
		//courseRepository.deleteById(1001L);
		
		//studentRepository.insertStudentWithPassport();
		
		/*
		List<Review> reviews = new ArrayList<>();
		Review review1 = new Review("5.0", "Good HandsOn course");
		Review review2 = new Review("4.85", "HatsOff Course");
		reviews.add(review1);
		reviews.add(review2);
				
		courseRepository.addReviewsToCourse(1003L, reviews);
		
		Student student = new Student("Vinay");
		Course course = new Course("Big Data EcoSystem in 1 month");
		
		studentRepository.insertStudentAndCourse_HC();
		studentRepository.insertStudentAndCourse(student, course);
		
		*/
		
		
		logger.info("========= Inheritance / Hierarchis - START  ============");
		
		/*
		Employee fte = new FullTimeEmployee("Anand", new BigDecimal(12000));
		Employee pte = new PartTimeEmployee("Vinay", new BigDecimal(95));
		
		employeeRepository.insertEmployee(fte);
		employeeRepository.insertEmployee(pte);
		
		
		//employeeRepository.insertEmployee(new FullTimeEmployee("Anand", new BigDecimal(12000)));
		//employeeRepository.insertEmployee(new PartTimeEmployee("Vinay", new BigDecimal(95)));

		

		//Can not used in MappedBy where Employee Table wont be created at database
		//logger.info("Retrive All Employees ==> {}", employeeRepository.retriveAllEmployees());
		
		
		logger.info("Retrive All FullTime Employees ==> {}", employeeRepository.retriveAllFullTimeEmployee());
		logger.info("Retrive All PartTime Employees ==> {}", employeeRepository.retriveAllPartTimeEmployee());
		
		
		logger.info("========= Inheritance / Hierarchis - END  ============");
		
		*/
		logger.info("========= CommanLine Runner - END  ============");
	}
}
