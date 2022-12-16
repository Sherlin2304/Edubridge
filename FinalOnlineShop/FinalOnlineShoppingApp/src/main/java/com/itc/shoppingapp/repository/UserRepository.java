package com.itc.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itc.shoppingapp.model.User;

//It indicates that the class provides mechanism for crud operation
@Repository
//JPA is java persistence api it interact with databases and perform db opration
//it internally implements hibernate
public interface UserRepository extends JpaRepository<User, Long> {

	//at the time of login This is method used,
	//Retrieve the information according to the user email from the database
	User findByEmail(String email);
}
