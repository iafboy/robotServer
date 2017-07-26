package io.pivotal.microservices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class HomeController {
	protected Logger logger = Logger.getLogger(HomeController.class
			.getName());
	
	@RequestMapping("/")
	public String home() {
		logger.info("go to index");
		return "index";
	}

}
