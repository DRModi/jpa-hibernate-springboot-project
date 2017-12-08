package com.training.jpa.hibernate.jpahibernatedemo.repository;

import static org.junit.Assert.assertEquals;

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


@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateDemoApplication.class)
public class CacheTest {

	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	@Transactional
	public void basic_first_levelCache(){
		
		Course course = em.find(Course.class, 1001l);
		logger.info("First Time Retriving Course 1001L ==> {}",course);
		
		Course course1 = em.find(Course.class, 1001l);
		logger.info("Second Time Retriving Course 1001L ==> {}",course1);
		
		assertEquals(course.getName(),course1.getName());
	}
}
