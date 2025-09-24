package com.cm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cm.model.CustomerDetails;
import com.cm.repository.CreditManagerRepo;
import com.cm.service.EmailServiceI;

@CrossOrigin("*")
@RestController
public class EmailController {
	@Autowired
	EmailServiceI esi;

	@Autowired
	CreditManagerRepo cr;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@GetMapping("/sendSanctionLetterMail/{customerID}")
	public CustomerDetails sendSanctionMail(@PathVariable("customerID")int customerID)
	{
		System.out.println("Mail sending started");
		Optional<CustomerDetails> customerdetail = cr.findById(customerID);
		CustomerDetails customerDetails = customerdetail.get();
		if (customerdetail.isPresent()) {
		    CustomerDetails customerDetails1 = customerdetail.get();
		    esi.sendSantionLetterMail(customerDetails1);
		    return customerDetails1;
		} else {
		    return null;
		}

	}

	}
	

