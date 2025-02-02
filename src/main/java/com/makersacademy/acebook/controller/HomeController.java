package com.makersacademy.acebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public RedirectView index() {
		return new RedirectView("/posts");
	}

	@GetMapping("/homepage")
	public String homepage() {
		return "/homepage/index";
	}

//	@GetMapping("/login")
//	public String newUser() {
//		return "/login";
//	}
}
