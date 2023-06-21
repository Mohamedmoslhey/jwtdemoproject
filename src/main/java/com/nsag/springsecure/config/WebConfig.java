package com.nsag.springsecure.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nsag.springsecure.config.JwtAuthenticationEntryPoint;
import com.nsag.springsecure.config.JwtRequestFilter;

import com.nsag.springsecure.repositry.UserRepositry;
import com.nsag.springsecure.service.UserService;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(pasw());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
		.anyRequest().authenticated().and().
		exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	@Bean
	public PasswordEncoder pasw(){
		return new BCryptPasswordEncoder();
	}
	/*
	
	
	
	
	@Bean
	@Primary
	public UserDetailsService loadUserByUsernqme() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<AppUser> apUserD = userRepositry.findByUserName(username);
				if(!apUserD.isPresent()){
					throw new UsernameNotFoundException("This User Not Exist >>"+username);
				}
				return new User(apUserD.get().getUserName(), apUserD.get().getPassword(), getAuth(apUserD.get()));
			}
		};
	}

	private List<GrantedAuthority> getAuth(AppUser apUserD) {
		List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		if(!apUserD.getAppRole().isEmpty()){
			apUserD.getAppRole().forEach(role->{
				grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
			});
		}
		// TODO Auto-generated method stub
		return grantedAuthority;
	}

*/}
