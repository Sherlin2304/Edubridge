package com.itc.shoppingapp.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
//To make this class has JPA entity and map this class as table in database
@Entity
//To provide Table Details + add unique key for that table
//In here table name is the class name, if we don't give it by default it will take class name as itself
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	//to make id as primary key
	@Id
	//value of pk generated automatically
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//to add this column name in database
	@Column(name ="first_name")
	private String firstName;
	
	@Column(name ="last_name")
	private String lastName;
	
	@Column(name ="email")
	private String email;
	
	@Column(name ="password")
	private String password;
	
	//to maintain many to many mapping between role and user
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//creating third table by using pk of user and role table
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_Id", referencedColumnName = "id"))
	
	//create collection here, because it has 
	//collection will store and manipulate group of object
	private Collection<Role> roles;
	public User() {
		
	}
	
	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
}
