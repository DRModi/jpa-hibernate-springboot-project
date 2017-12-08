------------------ Deltas For - Course ---------------

insert into course(id,name, created_date, last_updated_date, is_deleted) 
	values (1001, 'JPA Hibernate in 100 steps', sysdate(), sysdate(), false);
insert into course(id,name, created_date, last_updated_date, is_deleted) 
	values (1002, 'Spring Boot in 50 steps', sysdate(), sysdate(), false);
insert into course(id,name, created_date, last_updated_date, is_deleted) 
	values (1003, 'Microservice in 150 steps', sysdate(), sysdate(), false);

	-- insert into course(id,name, created_date, last_updated_date) 
	--values (1004, 'Delete Me', sysdate(), sysdate());

	
	
----------------- Deltas For - Passport ---------------

insert into passport(id, number) values (3001, 'E3001P');
insert into passport(id, number) values (3002, 'E3002P');
insert into passport(id, number) values (3003, 'E3003P');

	

----------------- Deltas For - Student ---------------

-- insert into student(id, name) values (2001, 'Anand');
-- insert into student(id, name) values (2002, 'Dipesh');
-- insert into student(id, name) values (2003, 'Churchill');

-- With Passport Id

insert into student(id, name, passport_id) values (2001, 'Anand', 3001);
insert into student(id, name, passport_id) values (2002, 'Dipesh', 3002);
insert into student(id, name, passport_id) values (2003, 'Churchill',3003);
	
	
	
---------------- Deltas For - Reviews ---------------

-- insert into review(id, rating, description) values (4001, 5, 'Great Course');
-- insert into review(id, rating, description) values (4002, 4.5, 'Wonderful Course');
-- insert into review(id, rating, description) values (4003, 5, 'Awesome Course');

insert into review(id, rating, description, course_id) values (4001, 5, 'Great Course',1001);
insert into review(id, rating, description, course_id) values (4002, 4.5, 'Wonderful Course',1001);
insert into review(id, rating, description, course_id) values (4003, 5, 'Awesome Course',1003);


----------------- Deltas For - Student_Course ---------------

insert into student_course(student_id, course_id) values (2001, 1001);
insert into student_course(student_id, course_id) values (2002, 1001);
insert into student_course(student_id, course_id) values (2003, 1001);
insert into student_course(student_id, course_id) values (2001, 1003);

