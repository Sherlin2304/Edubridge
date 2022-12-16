package com.itc.shoppingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//To make this class has JPA entity and map this class as table in database
@Entity
//To provide Table Details + add unique key for that table
//In here table name is the class name, if we don't give it by default it will take class name as itself
@Table (name = "role")
public class Role {

	//to make id as primary key
	@Id
	//value of pk generated automatically
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private String name;
	
	public Role() {
		
	}
	
	public Role(String name) {
		super();
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
