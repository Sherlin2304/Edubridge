package com.itc.shoppingapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.itc.shoppingapp.dto.UserRegistrationDto;
import com.itc.shoppingapp.model.User;

//UserDetailsService interface used to retive user related data(by using loaduserbyname())

public interface UserService extends UserDetailsService{

	//after taking user data in dto object
	//save user's registered data in database via in user object because user class uses userrepository
	User save(UserRegistrationDto registrationDto);
}
