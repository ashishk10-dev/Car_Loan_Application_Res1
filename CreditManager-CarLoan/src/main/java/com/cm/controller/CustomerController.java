package com.cm.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cm.model.CustomerDetails;

import com.cm.service.CreditManagerServiceI;


@CrossOrigin("*")
@RestController
public class CustomerController {
	@Autowired
	CreditManagerServiceI ssi;

	@PostMapping("/addCustomerData")
	public ResponseEntity<CustomerDetails> addData(@RequestBody CustomerDetails customer)
	{
		CustomerDetails cd= ssi.addData(customer);
		return new ResponseEntity<CustomerDetails>(cd,HttpStatus.CREATED);
				
	}

	//update customer loan application sanction status
	@PutMapping("/updateSanctionstatus/{customerID}")
	public ResponseEntity<CustomerDetails> updateSanctionStatus(@PathVariable int customerID,@RequestBody CustomerDetails customer)
	{
		CustomerDetails  cust=ssi.updateSanctionStatus(customerID,customer);
		return new ResponseEntity<CustomerDetails>(cust,HttpStatus.OK);
		
	}


	@GetMapping("/getVerifiedCustomersAPI")
	public ResponseEntity<List<CustomerDetails>> getVerifiedCustomers() {
	    List<CustomerDetails> list = ssi.getVerifiedCustomers();

	    if (list != null && !list.isEmpty()) {
	    	 for (CustomerDetails customer : list) {
	             System.out.println(customer);
	             System.out.println(customer.getCustomerID());// Assuming CustomerDetails has a proper toString()
	         }
	    	System.out.println(list.getClass());
	        return new ResponseEntity<>(list, HttpStatus.OK);
	    } else {
	        return ResponseEntity.noContent().build();
	    }
	}



	@GetMapping("/OfferedData/{status}")
	public ResponseEntity<List<CustomerDetails>> showOfferedData(@PathVariable String status)
	{
	   List<CustomerDetails> coslist=ssi.getOfferedData(status);
	   return new ResponseEntity<List<CustomerDetails>>(coslist,HttpStatus.OK);

	}
	

	
	 @GetMapping("/rejected")
	    public ResponseEntity<List<CustomerDetails>> getAllRejectedSanctions() {
	        List<CustomerDetails> list = ssi.getAllRejectedSanctionLetters();
	        if (list.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.ok(list);

	 }
	

	 
	 @GetMapping("/AcceptedData/{status}")
	 public ResponseEntity<List<CustomerDetails>> showVerifiedData(@PathVariable String status) {
	     List<CustomerDetails> coslist = ssi.getAcceptedData(status);
	     
	     if (coslist != null && !coslist.isEmpty()) {
	         return new ResponseEntity<>(coslist, HttpStatus.OK);
	     } else {
	         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	     }
	 }

}