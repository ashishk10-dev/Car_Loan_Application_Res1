package com.oe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oe.model.CustomerLoanApplication;
import com.oe.service.CustomerLoanApplicationServiceI;

@CrossOrigin("*")
@RestController
public class CustomerLoanController {
	
	@Autowired
	CustomerLoanApplicationServiceI csi;
	
	
	
	
	@PutMapping("/update/{customerID}")
	public ResponseEntity<CustomerLoanApplication> updateLoanStatus(@PathVariable("customerID")int customerID,
			                                                        @RequestBody CustomerLoanApplication customer)
	{
		CustomerLoanApplication csa= csi.updateLoanStatus(customerID,customer);
		return new  ResponseEntity<CustomerLoanApplication>(csa,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/verificationStatus/{customerID}")
	public ResponseEntity<CustomerLoanApplication> updateVerificationStatus(@PathVariable int customerID,
													@RequestBody CustomerLoanApplication customer )
	{
		CustomerLoanApplication custVerify=csi.updateVerificationStatus(customerID,customer);
		return new ResponseEntity<CustomerLoanApplication>(custVerify,HttpStatus.OK);
	}

}
