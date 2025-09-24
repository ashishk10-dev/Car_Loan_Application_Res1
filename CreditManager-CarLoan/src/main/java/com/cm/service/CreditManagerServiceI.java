package com.cm.service;

import java.util.List;

import com.cm.model.CustomerDetails;


public interface CreditManagerServiceI {

	public CustomerDetails addData(CustomerDetails customer);

	public CustomerDetails updateSanctionStatus(int customerID, CustomerDetails customer);


	//Get All Verified Data in CreditManagerServiceI
	public List<CustomerDetails> getVerifiedCustomers();

	public List<CustomerDetails> getOfferedData(String status);

	public List<CustomerDetails> getAllRejectedSanctionLetters();

	public List<CustomerDetails> getAcceptedData(String status);


}
