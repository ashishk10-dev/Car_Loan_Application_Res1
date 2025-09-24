package com.er.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.er.model.EREnquiry;
import com.er.service.ERServiceI;


import randomuserpass.CredentialGenerator;


@CrossOrigin("*")

@RestController
public class ERController {
	@Autowired
	ERServiceI esi;

	
@GetMapping("/enquirystatusapproved")
  public ResponseEntity<List<EREnquiry>> getAllApprovedCustomers() {
	List<EREnquiry> enquiry = esi.getApprovedEnquiry();
       return ResponseEntity.ok(enquiry);
     }
  
      @DeleteMapping("/delete/{customerID}")
	   public ResponseEntity<String> deleteByCustomerId(@PathVariable int customerID)
	    {
	     	esi.deleteCustomer(customerID);
		    return new ResponseEntity<String>("data deleted ",HttpStatus.NO_CONTENT);
	    }
      
	//get Single customer by ID
@GetMapping("/getSingle/{customerID}")
	public ResponseEntity<EREnquiry> getSingleCustomer(@PathVariable int customerID)
	{
		EREnquiry customer=esi.getSingle(customerID);
		return new ResponseEntity<EREnquiry>(customer,HttpStatus.OK);
	}
	
	@GetMapping("/rejectedEnq/{enquiryStatus}")
	public ResponseEntity<List<EREnquiry>> showRejectedEnquiry(@PathVariable String enquiryStatus)
	{
	List<EREnquiry> enqList=esi.getRejectedEnquiry(enquiryStatus);
	return new ResponseEntity<List<EREnquiry>>(enqList,HttpStatus.OK);
	}


//creating and saving enquiry	
//	@PostMapping("/createEnquiryAPI")
//	public ResponseEntity<EREnquiry> createEREnquiry(@RequestBody EREnquiry enquiry)
//	{
//		EREnquiry erenq = esi.createEREnquiry(enquiry);
//		return new ResponseEntity<EREnquiry>(erenq,HttpStatus.CREATED);
//	}
	@PostMapping("/createEnquiryAPI")
	public ResponseEntity<EREnquiry> createEREnquiry(@RequestBody EREnquiry enquiry) {
	    // Generate and set credentials
	    if (enquiry.getFirstName() != null && enquiry.getLastName() != null) {
	        CredentialGenerator.generateAndSetCredentials(enquiry);
	    }

	    EREnquiry erenq = esi.createEREnquiry(enquiry);
	    return new ResponseEntity<>(erenq, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<EREnquiry> deleteData()
	{
		EREnquiry en=esi.deleteAll();
		return new ResponseEntity<EREnquiry>(en,HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<EREnquiry>>  getAll()
	{
		List<EREnquiry> list= esi.getAll();
		return new ResponseEntity<List<EREnquiry>>(list,HttpStatus.OK);
		
	}
	

	@PutMapping("enquiry/{customerID}")
	public ResponseEntity<String> updateData(@RequestBody EREnquiry e, @PathVariable int customerID) {
	    EREnquiry oldid = esi.updateData(e, customerID);


   if (oldid != null) {
	        return new ResponseEntity<>("Data updated.", HttpStatus.ACCEPTED);
	    } else {
	        return new ResponseEntity<>("Employee not found.", HttpStatus.NOT_FOUND);
	    }
      }
	
	@GetMapping("/getAllEnquiry")
		public ResponseEntity<List<EREnquiry>> getAllEnquiry()
		{
			List<EREnquiry> ERlist = esi.getEREntity();
			return new ResponseEntity<List<EREnquiry>>(ERlist,HttpStatus.OK);
		}
	
	
	@GetMapping("/getPendingData")
	public ResponseEntity<List<EREnquiry>> getPendingEnquiries()
	{
		List<EREnquiry> list=esi.getPendingQueries();
		return new ResponseEntity<List<EREnquiry>>(list,HttpStatus.OK);
	}

	
	

}

