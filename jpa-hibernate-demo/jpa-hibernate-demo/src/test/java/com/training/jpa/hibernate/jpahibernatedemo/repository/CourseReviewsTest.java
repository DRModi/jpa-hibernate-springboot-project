package com.training.jpa.hibernate.jpahibernatedemo.repository;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
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
import com.training.jpa.hibernate.jpahibernatedemo.entity.Review;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateDemoApplication.class)
public class CourseReviewsTest {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	@Transactional
	public void retriveCourseAndReviews() {

		Course course = em.find(Course.class, 1001l);
		List<Review> reviews = course.getReviews();

		logger.info("Retrived Course Details ===> {}", course);

		Iterator itr = reviews.iterator();

		int i = 1;
		
		
		while(itr.hasNext()) {
			Review review = (Review) itr.next();
			logger.info("Retrived Reviews - " + i + " ==> {}", review);
			i++;
		}
		
		logger.info("Total Reviews Course Details ===> {}", reviews);

		assertEquals("JPA Hibernate in 100 steps", course.getName());
	}
	
	@Test
	@Transactional
	public void retriveReviewsAndCourse(){
		
		Review review = em.find(Review.class, 4001L);
		
		Course course = review.getCourse();
		
		logger.info("Review 4001 ==> {}", review);
		logger.info("Associated Course to Review 4001 ==> {}", course);
		
		assertEquals("5", review.getRating());
		
		
	}

}
