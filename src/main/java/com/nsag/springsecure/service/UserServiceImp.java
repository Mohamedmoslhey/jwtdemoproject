package com.nsag.springsecure.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nsag.springsecure.entity.UserDao;
import com.nsag.springsecure.repositry.UserRepositry;



@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepositry userRepositry;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserDao> apUserD = userRepositry.findByUsername(username);
		if(!apUserD.isPresent()){
			throw new UsernameNotFoundException("This User Not Exist >>"+username);
		}
		return new User(apUserD.get().getUsername(), apUserD.get().getPassword(),
				new ArrayList<>());
		//return new User(apUserD.get().getUserName(), apUserD.get().getPassword(), getAuth(apUserD.get()));
	}

	@Override
	public UserDao save(UserDao userDao) {
		userDao.setPassword(new BCryptPasswordEncoder().encode(userDao.getPassword()));
		return userRepositry.save(userDao);
		
	}

	@Override
	public Optional<UserDao> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepositry.findByUsername(username);
	}

	/*private List<GrantedAuthority> getAuth(AppUser apUserD) {
		List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		if(!apUserD.getAppRole().isEmpty()){
			apUserD.getAppRole().forEach(role->{
				grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
			});
		}
		// TODO Auto-generated method stub
		return grantedAuthority;
	}
*/
}
