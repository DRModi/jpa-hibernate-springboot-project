package com.training.jpa.hibernate.jpahibernatedemo.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;


//Single Table
//@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="EmployeeType")
//@Entity

//Table per class
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@Entity


//Joined Table
//@Inheritance(strategy=InheritanceType.JOINED)
//@Entity

//Alternative of Inheritance - 
@MappedSuperclass
//@Entity - Entity need to be commented out
public abstract class Employee{

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	
	protected Employee(){
		
	}
	
	public Employee(String name){
		this.name=name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Employee [id=%s, name=%s]", id, name);
	}
	
}
