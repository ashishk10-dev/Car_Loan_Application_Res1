package com.oe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.RestController;


import com.oe.model.EREnquiry;
import com.oe.service.EREnquiryServiceI;

@CrossOrigin("*")
@RestController
public class HomeController {
	@Autowired
	EREnquiryServiceI esi;
	


	@GetMapping("/getCibil")
	public ResponseEntity<List<EREnquiry>> getCibilPending(){
		
		List<EREnquiry> list= esi.getCibil();
		return new ResponseEntity<List<EREnquiry>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getSingle/{customerID}")
	public ResponseEntity<EREnquiry> getCibilSingle(@PathVariable int customerID)
	{
		EREnquiry enq=esi.getSingleCibil(customerID);
		return new ResponseEntity<EREnquiry>(enq,HttpStatus.OK);
	}


}
