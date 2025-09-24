package com.er.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.er.model.CustomerLoanApplication;

public interface CustomerLoanApplicationServiceI {

	public List<CustomerLoanApplication> getAllLoanData();

	public CustomerLoanApplication getLoanById(int customerID);

	public CustomerLoanApplication addLoanData(String loanDataJson, MultipartFile addressProof, MultipartFile panCard,
			MultipartFile aadharCard, MultipartFile photo, MultipartFile signature, MultipartFile bankCheque,
			MultipartFile salarySlips,MultipartFile incomeTax) throws IOException;

	public CustomerLoanApplication updateLoanData(String loanData, MultipartFile addressProof, MultipartFile panCard,
			MultipartFile aadharCard, MultipartFile photo, MultipartFile signature, MultipartFile bankCheque,
			MultipartFile salarySlips, MultipartFile incomeTax) throws IOException;


	public List<CustomerLoanApplication> getAllSubmittedData();

	public List<CustomerLoanApplication> getVerifiedData(String loanStatus);

	public List<CustomerLoanApplication> getAllApplication();

	public List<CustomerLoanApplication> getNotVerifiedData(String loanStatus);

	public CustomerLoanApplication getSingleCustomerData(int customerID);


}
