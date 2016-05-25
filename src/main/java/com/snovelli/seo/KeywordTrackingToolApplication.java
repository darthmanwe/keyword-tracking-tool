package com.snovelli.seo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class KeywordTrackingToolApplication {



	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Go away!";
	}

	public static void main(String[] args) {
		SpringApplication.run(KeywordTrackingToolApplication.class, args);
	}
}
