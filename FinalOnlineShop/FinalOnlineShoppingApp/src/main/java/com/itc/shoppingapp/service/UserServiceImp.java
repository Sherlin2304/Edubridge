package com.itc.shoppingapp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itc.shoppingapp.dto.UserRegistrationDto;
import com.itc.shoppingapp.model.Role;
import com.itc.shoppingapp.model.User;
import com.itc.shoppingapp.repository.UserRepository;

//used in service layer which provide business logic& annotate the classes as service provider 
//spring context autodetect this classes at time of scanning
@Service
public class UserServiceImp implements UserService{

	//@Autowired is field base Injection for UserRepository interface but here we are using constructor base injection

	private UserRepository userRepository;
	
	//it will inject object dependency automatically
	@Autowired
	//used to encrypt the password
	private BCryptPasswordEncoder passwordEncoder;

	//constructor based injection for userRepository
	public UserServiceImp(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	//create save method to save user data to the database by using dto object
	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(),
				registrationDto.getLastName(),
				registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()),
				Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}

	//connect with UserRepository
	//at the time login check whether user email is present in database
	//by using UserDetailsService loaduserbyusername()
	@Override
	//UserDetails is interface User is class which implements UserDetails
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		//if that user is present in db create new User object which belong to spring security
		//with mail,password,role
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	//method to retrive roll and 
	
	//covert rows into authority beacuse spring security expect authority
	private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		//map rows to authority
		//convert roles into stream and map them authority using map functiona along with lambda expression
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
