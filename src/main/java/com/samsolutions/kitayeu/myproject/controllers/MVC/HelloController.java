package com.samsolutions.kitayeu.myproject.controllers.MVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HelloController {


	@GetMapping
	public String getIndex(Model model) {
		model.addAttribute("name", "this is my first spring application");
		return "hello";
	}
}

