package com.itc.shoppingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//it will indicate this class as spring mvc controller
//it can handel web request
@Controller
public class MainController {

	//it will handel http get request with /login url & return login.html
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	//it will handel http get request with "/" url & return home.html
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
