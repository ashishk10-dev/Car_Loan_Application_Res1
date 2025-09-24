package com.oe.service;

import com.oe.model.CustomerLoanApplication;

public interface CustomerLoanApplicationServiceI {

	public CustomerLoanApplication updateLoanStatus(int customerID, CustomerLoanApplication customer);

	public CustomerLoanApplication updateVerificationStatus(int customerID, CustomerLoanApplication customer);

}
