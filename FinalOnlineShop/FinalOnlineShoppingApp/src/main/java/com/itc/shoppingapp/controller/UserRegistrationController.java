package com.itc.shoppingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itc.shoppingapp.dto.UserRegistrationDto;
import com.itc.shoppingapp.service.UserService;

//it will indicate this class as spring mvc controller
//it can handel web request
@Controller
//maps web request onto particular handler(class or method)
@RequestMapping("/registration")
public class UserRegistrationController {

	//automatic dependency injection
	//spring wil create object/bean for this class/interface
	@Autowired
	private UserService userService;

	//constructor based injecion
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	//
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	//this method returns view of regisrationform
	//getmapping because this is http_get request
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	//postmapping because this is http_post request
	//this method wil store user info.at the time of registraion by using UserService save method
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";//?success this connect with register.html page
		
	}
}
