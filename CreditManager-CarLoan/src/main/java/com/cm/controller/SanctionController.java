package com.cm.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cm.model.CustomerDetails;
import com.cm.model.SanctionLetter;
import com.cm.service.SanctionServiceI;


@CrossOrigin("*")
@RestController
public class SanctionController {
	@Autowired
	SanctionServiceI ssi;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	
//	@PutMapping("/generatePdf/{customerID}")
//	public CustomerDetails updateSactionLetter(@PathVariable("customerID") int customerID, @RequestBody SanctionLetter sanctionLetter) {
//
//			return ssi.generateSactionId(customerID, sanctionLetter);
//	}
	@PutMapping("/generatePdf/{customerID}")
	public CustomerDetails updateSactionLetter(@PathVariable("customerID") int customerID, @RequestBody SanctionLetter sanctionLetter) {

			return ssi.generateSactionId(customerID, sanctionLetter);
	}
	
	}
