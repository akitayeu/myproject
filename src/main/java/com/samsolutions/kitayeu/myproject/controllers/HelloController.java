package com.samsolutions.kitayeu.myproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {


	@GetMapping("/api")
	public String getIndex(Model model) {
		model.addAttribute("name", "this is my first spring application");
		return "hello";
	}
}
