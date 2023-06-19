package com.nsag.springsecure.controller.validate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JwtValidateAccessFromUserController {
////class for validate user name and password
	
	//@GetMapping({"/id"})
	@GetMapping({ "/hello" })
	public String firstPage(){
		
		
		return "Hello World";
	}
}
