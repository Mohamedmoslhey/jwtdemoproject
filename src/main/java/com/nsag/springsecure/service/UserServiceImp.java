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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImp implements UserService {



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Optional<AppUser> apUserD = userRepositry.findByUserName(username);
		if(!username.equals("javainuse")){
			throw new UsernameNotFoundException("This User Not Exist >>"+username);
		}
		return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
				new ArrayList<>());
		//return new User(apUserD.get().getUserName(), apUserD.get().getPassword(), getAuth(apUserD.get()));
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
