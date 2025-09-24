package com.ci.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class HomeController {
	
	private final static Random r= new Random();
	
	@GetMapping("/getcibil")
	public int createCibil()
	{
		int i=r.nextInt(300,900);
		return i;
	}
	

}
