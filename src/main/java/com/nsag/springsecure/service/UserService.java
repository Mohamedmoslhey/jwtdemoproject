package com.nsag.springsecure.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nsag.springsecure.entity.UserDao;
import com.nsag.springsecure.repositry.UserRepositry;



public interface UserService  extends UserDetailsService{
	/*public List<AppUser> findAll();
	public Optional<AppUser> findById(Long id);*/
	public UserDao save(UserDao userDao);
	public Optional<UserDao> findByUsername(String userName);

}
