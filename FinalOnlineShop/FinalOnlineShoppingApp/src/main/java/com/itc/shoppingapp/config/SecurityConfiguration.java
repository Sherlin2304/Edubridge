
+-package com.itc.shoppingapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.itc.shoppingapp.service.UserService;

//Indicates that class is source of bean defination
@Configuration
//EnableWebSecurity integrates spring_security with spring_mvc to enable web_security support
@EnableWebSecurity
//WebSecurityConfigurerAdapter provide overloaded custom method to provide configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	//automatic dependency injection
	//spring wil create object/bean for this class/interface
	@Autowired
	private UserService userService;
	
	//bean for BCryptPasswordEncoder to encode the password
	@Bean  //used to specify that this method returns bean to be managed by spring_context
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();}
	
	
	//DaoAuthenticationProvider used to intgrate spring_security and spring_data_jpa hibernate and provide its bean i.e.auth 
    @Bean //used to specify that this method returns bean to be managed by spring_context
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
    //to pass authenticationProvider() to cnfig method override that method
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//passing authenticationProvider()...
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	//This method include all spring security configuration method such as static method and url
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//configure URL
		http.authorizeRequests().antMatchers(
				"/registration**",
				"/js/**",
				"/css/**",
				"/img/**").permitAll()
				//authenticate any request
				.anyRequest().authenticated()
				.and()
				.formLogin()
				//provided url for custom login page
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				//logout url cofiguration
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				//after clicking logout navigate to login page
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}
}
