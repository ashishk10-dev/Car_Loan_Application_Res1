package com.er.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.er.model.EREnquiry;
import com.er.service.ERServiceI;

import randomuserpass.CredentialGenerator;
@RestController
public class CustomerController {
	@Autowired
	ERServiceI esi;

	      
	//get Single customer by ID
	@GetMapping("/getByCustomerSingle/{customerID}")
	public ResponseEntity<EREnquiry> getSingleCustomer(@PathVariable int customerID)
	{
		EREnquiry customer=esi.getSingle(customerID);
		System.out.println(customerID);
		return new ResponseEntity<EREnquiry>(customer,HttpStatus.OK);
	}
	
	//create enquiry as customer
	@PostMapping("/createEnquiryByCustomerAPI")
	public ResponseEntity<EREnquiry> createEREnquiry(@RequestBody EREnquiry enquiry) {
	    // Generate and set credentials
	    if (enquiry.getFirstName() != null && enquiry.getLastName() != null) {
	        CredentialGenerator.generateAndSetCredentials(enquiry);
	    }

	    EREnquiry erenq = esi.createEREnquiry(enquiry);
	    return new ResponseEntity<>(erenq, HttpStatus.CREATED);
	}
	
//	Update or edit by customer
	@PutMapping("enquiryCustomerUpdate/{customerID}")
	public ResponseEntity<String> updateData(@RequestBody EREnquiry e, @PathVariable int customerID)
	{
	    EREnquiry oldid = esi.updateData(e, customerID);
	    
	    if (oldid != null) 
	    {
	        return new ResponseEntity<>("Data updated.", HttpStatus.ACCEPTED);
	    } 
	    else {
	        	return new ResponseEntity<>("Employee not found.", HttpStatus.NOT_FOUND);
	    	 }
	}
	
//	Delete by customer by id
	@DeleteMapping("/deleteByCustomer/{customerID}")
	   public ResponseEntity<String> deleteByCustomerId(@PathVariable int customerID)
	    {
	     	esi.deleteCustomer(customerID);
		    return new ResponseEntity<String>("data deleted ",HttpStatus.OK);
	    }
	
}
