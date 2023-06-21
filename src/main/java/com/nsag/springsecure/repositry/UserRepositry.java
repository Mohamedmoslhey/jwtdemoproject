package com.nsag.springsecure.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsag.springsecure.entity.UserDao;

@Repository
public interface UserRepositry extends JpaRepository<UserDao, Long> {

}
