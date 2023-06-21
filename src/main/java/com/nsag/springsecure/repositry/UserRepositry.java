package com.nsag.springsecure.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsag.springsecure.entity.UserDao;

@Repository
public interface UserRepositry extends JpaRepository<UserDao, Long> {

	public Optional<UserDao> findByUsername(String username);

}
