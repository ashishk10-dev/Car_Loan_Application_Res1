package com.er.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.er.exceptions.AgeException;
import com.er.exceptions.InvalidEmailException;
import com.er.exceptions.InvalidLastNameException;
import com.er.exceptions.InvalidMobileNumberException;
import com.er.exceptions.InvalidNameException;
import com.er.exceptions.InvalidPanCardException;
import com.er.model.AllPersonalDocuments;
import com.er.model.CustomerLoanApplication;
import com.er.model.EREnquiry;
import com.er.repository.ERRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public  class ERServiceImpl implements ERServiceI{
     @Autowired
	ERRepository er;

	@Override
      public void deleteCustomer(int customerID) {
		 er.deleteById(customerID);
		
	}

	@Override
	public List<EREnquiry> getApprovedEnquiry() {
		
		    return er.findByEnquiryStatusIgnoreCase("Approved");
		}

	@Override
	public EREnquiry getSingle(int customerID) {
		Optional<EREnquiry> optional=er.findById(customerID);
		if(optional.isPresent())
		{
			EREnquiry enquiry=optional.get();
			return enquiry;
		}else {
		
		 throw new RuntimeException(" Customer data is not available..");
	}
	}
	

	@Override
	public List<EREnquiry> getRejectedEnquiry(String enquiryStatus) {
	
		    if ("Rejected".equalsIgnoreCase(enquiryStatus)) {
		        return er.findByEnquiryStatusIgnoreCase(enquiryStatus);
		    }
		    return new ArrayList<>();
		}


		

    @Override
	public EREnquiry createEREnquiry(EREnquiry enquiry) {
		
		enquiry.setEnquiryStatus("Cibil Pending");
			
//FirstName
		if(enquiry.getFirstName()==null || enquiry.getFirstName().trim().isEmpty()) {
			throw new InvalidNameException("Name is Required Please...!");
		}
		if(!enquiry.getFirstName().matches("^[A-Za-z]+$")) {
		    throw new InvalidNameException("Last name must contain only letters");
		}

//LastName
		if(enquiry.getLastName() == null || enquiry.getLastName().trim().isEmpty()) {
		    throw new InvalidLastNameException("Last name is required");
		}
		if(!enquiry.getLastName().matches("^[A-Za-z]+$")) {
		    throw new InvalidLastNameException("Last name must contain only letters");
		}
//Age
		if(enquiry.getAge()<18 || enquiry.getAge()>65) {
			throw new AgeException("You age is not valid for loan...!");			
		}
		
//Mobile Number	
		String mobileStr=String.valueOf(enquiry.getMobileNo());
		if(mobileStr==null) {
			throw new InvalidMobileNumberException("Mobile number is required...!");
		}
		if(!mobileStr.matches("\\d+")) {
			throw new InvalidMobileNumberException("Mobile number must contain only digits");
		}
		
		if(mobileStr.length() != 10) {
			throw new InvalidMobileNumberException("Mobile number must contain only 10 digits");
		}
//Email 
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		if(enquiry.getEmail()==null) {
			throw new InvalidEmailException("Email Is Required...!!!"); 
		}
		if(!enquiry.getEmail().matches(emailRegex)) {
			throw new InvalidEmailException("Please Enter valid email format (example: name@gmail.com)");
		}
//Pan Card
		String panRegex = "^[A-Z]{5}[0-9]{4}[A-Z]$";
		if(!enquiry.getPanCardNo().matches(panRegex)) {
			throw new InvalidPanCardException("Invalid PAN card format. Format must be 5 capital letters, 4 digits, 1 capital letter (e.g., ABCDE1234F)");
		}


		EREnquiry enq=er.save(enquiry);
		return enq;
	}

	public EREnquiry deleteAll() {
	er.deleteAll();
	return null;

	}
	
	@Override
	public List<EREnquiry> getAll() 
	{
	    List<EREnquiry> all=er.findAll();
		return all;
	}
	
	@Override
	public List<EREnquiry> getEREntity() {
		
		return er.findAll();
	}

	@Override
	public EREnquiry updateData(EREnquiry e, int customerID) {
		Optional<EREnquiry> optional=er.findById(customerID);
		if(optional.isPresent()) {
			EREnquiry existing = optional.get();
			
			existing.setFirstName(e.getFirstName());
			existing.setLastName(e.getLastName());
			existing.setAge(e.getAge());
			existing.setEmail(e.getEmail());
			existing.setMobileNo(e.getMobileNo());
			existing.setPanCardNo(e.getPanCardNo());
			existing.setCibil(e.getCibil());
			existing.setEnquiryStatus(e.getEnquiryStatus());
			return er.save(existing);
		}
		else {
		return null;
		}
	}



	@Override
	public List<EREnquiry> getPendingQueries() {
		
		 return er.findByEnquiryStatusIgnoreCase("Cibil Pending");
	}

	


	
	
	
	

}

	



	
	
