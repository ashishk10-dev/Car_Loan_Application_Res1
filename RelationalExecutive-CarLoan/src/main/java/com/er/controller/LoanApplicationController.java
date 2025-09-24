package com.er.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.er.model.CustomerLoanApplication;
import com.er.model.EREnquiry;
import com.er.service.CustomerLoanApplicationServiceI;
import com.er.service.ERServiceI;

@CrossOrigin("*")
@RestController
public class LoanApplicationController {
	@Autowired
	ERServiceI esi;
	
	@Autowired
	CustomerLoanApplicationServiceI csi;
	
//	@PostMapping(value = "/addLoanData", consumes="multipart/form-data")
	
//	@PostMapping(value = "/addLoanData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PostMapping(value = "/addLoanData", consumes="multipart/form-data")
	public ResponseEntity<CustomerLoanApplication> addLoanData(
			@RequestPart("loanData") String loanData,
	    @RequestPart("addressProof") MultipartFile addressProof,
	    @RequestPart("panCard") MultipartFile panCard,
	    @RequestPart("aadharCard") MultipartFile aadharCard,
	    @RequestPart("photo") MultipartFile photo,
	    @RequestPart("signature") MultipartFile signature,
	    @RequestPart("bankCheque") MultipartFile bankCheque,
	    @RequestPart("salarySlips") MultipartFile salarySlips,
	    @RequestPart("incomeTax")MultipartFile incomeTax
	) throws IOException  {
	    
	    CustomerLoanApplication cla = csi.addLoanData(loanData, addressProof, panCard, aadharCard,
	        photo, signature, bankCheque,salarySlips,incomeTax
	    );

	   return new ResponseEntity<CustomerLoanApplication>(cla,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllLoanData")
	public ResponseEntity<List<CustomerLoanApplication>> getAllData()
	{
		List<CustomerLoanApplication> list=csi.getAllLoanData();
		return new ResponseEntity<List<CustomerLoanApplication>>(list,HttpStatus.OK);
	}

	
	@GetMapping("/getLoanById/{customerID}")
	public ResponseEntity<CustomerLoanApplication> getLoanById(@PathVariable int customerID)
	{
		CustomerLoanApplication loanId=csi.getLoanById(customerID);
		return new ResponseEntity<CustomerLoanApplication>(loanId,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/updateLoanData")
	public ResponseEntity<CustomerLoanApplication> updateLoanData(
	        @RequestPart("loanData") String loanData,
	        @RequestPart(value = "addressProof", required = false) MultipartFile addressProof,
	        @RequestPart(value = "panCard", required = false) MultipartFile panCard,
	        @RequestPart(value = "aadharCard", required = false) MultipartFile aadharCard,
	        @RequestPart(value = "photo", required = false) MultipartFile photo,
	        @RequestPart(value = "signature", required = false) MultipartFile signature,
	        @RequestPart(value = "bankCheque", required = false) MultipartFile bankCheque,
	        @RequestPart(value = "salarySlips", required = false) MultipartFile salarySlips,
	        @RequestPart(value = "incomeTax", required = false) MultipartFile incomeTax
	) throws IOException {
	    CustomerLoanApplication updated = csi.updateLoanData(loanData, addressProof, panCard, aadharCard, photo, signature, bankCheque, salarySlips, incomeTax);
	    return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@GetMapping("verifiedData/{loanStatus}")
	public ResponseEntity<List<CustomerLoanApplication>> showVerifiedData(@PathVariable String loanStatus)
	{
	   List<CustomerLoanApplication> coslist=csi.getVerifiedData(loanStatus);
	   return new ResponseEntity<List<CustomerLoanApplication>>(coslist,HttpStatus.OK);
	}


	@GetMapping("/allApplication")
	public ResponseEntity<List<CustomerLoanApplication>> getAllLoanApplication()
	{
		List<CustomerLoanApplication> applist=csi.getAllApplication();
		return new ResponseEntity<List<CustomerLoanApplication>>(applist,HttpStatus.OK);
	}
//get all notverified customers 
	@GetMapping("/notVerified/{loanStatus}")
	public ResponseEntity<List<CustomerLoanApplication>> getAllNotVerifiedData(@PathVariable String loanStatus)
	{
		List<CustomerLoanApplication> notVerifList=csi.getNotVerifiedData(loanStatus);
		return new ResponseEntity<List<CustomerLoanApplication>>(notVerifList,HttpStatus.OK);
	}

	@GetMapping("/AllSubmittedData")
	public ResponseEntity<List<CustomerLoanApplication>> getAllSubmittedData()
	{
		List<CustomerLoanApplication> list1=csi.getAllSubmittedData();
		return new ResponseEntity<List<CustomerLoanApplication>>(list1,HttpStatus.OK);
	}
	
	@GetMapping("/getCustomerLoanData/{customerID}")
	public ResponseEntity<CustomerLoanApplication> getSingleCustomerData(@PathVariable int customerID)
	{
		CustomerLoanApplication cust=csi.getSingleCustomerData(customerID);
		return new ResponseEntity<CustomerLoanApplication>(cust,HttpStatus.OK);
	}

}
