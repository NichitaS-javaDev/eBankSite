package com.ebank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class LoginController {
	
	@GetMapping("/")
	public String getLoginPage() {
		return "index";
	}
	
	/*@GetMapping("/sign-in")
	public String getLoginPage() {
		return "index";
	}*/
}
