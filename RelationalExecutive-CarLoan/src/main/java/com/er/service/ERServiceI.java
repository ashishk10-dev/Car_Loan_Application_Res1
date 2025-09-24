package com.er.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.er.model.CustomerLoanApplication;
import com.er.model.EREnquiry;

public interface ERServiceI {

	public void deleteCustomer(int customerID);

	public List<EREnquiry> getApprovedEnquiry();
 

	public EREnquiry getSingle(int customerID);

	public List<EREnquiry> getRejectedEnquiry(String enquiryStatus);

	 public List<EREnquiry> getAll();


	EREnquiry createEREnquiry(EREnquiry enquiry);

	public EREnquiry deleteAll();

	public EREnquiry updateData(EREnquiry e, int customerID);

	public List<EREnquiry> getEREntity();

	public List<EREnquiry> getPendingQueries();

	





}

