package com.nsag.springsecure.controller.generate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nsag.springsecure.config.JwtTokenUtil;
import com.nsag.springsecure.entity.JwtRequest;
import com.nsag.springsecure.entity.JwtResponse;
import com.nsag.springsecure.service.UserService;

@RestController
public class JwtGenerateController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping({"/authenticate"})
public ResponseEntity<?> CreateAuthToken(@RequestBody JwtRequest jwtreq) throws Exception{
		authenticate(jwtreq.getUsername(), jwtreq.getPassword());

		;

		final String token = jwtTokenUtil.generateToken(userService.loadUserByUsername(jwtreq.getUsername()));

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
	}
	
	/*@GetMapping("/{id}")
	public ResponseEntity<?> findbyId(@PathVariable Long id){
		
		
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@GetMapping()
	public ResponseEntity<?> findall(){
		
		return ResponseEntity.ok(userService.findAll());
	}*/
}
