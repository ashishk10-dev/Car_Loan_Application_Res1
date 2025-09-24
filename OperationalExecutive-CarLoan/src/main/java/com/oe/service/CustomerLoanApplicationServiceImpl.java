package com.oe.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oe.model.CustomerLoanApplication;
import com.oe.model.CustomerVerification;
import com.oe.repository.CustomerLoanApplicationRepo;


@Service
public class CustomerLoanApplicationServiceImpl implements CustomerLoanApplicationServiceI{
	
	@Autowired
	CustomerLoanApplicationRepo cr;

	@Override
	public CustomerLoanApplication updateLoanStatus(int customerID, CustomerLoanApplication customer) {
		
		Optional<CustomerLoanApplication> optional=cr.findById(customerID);
		if(optional.isPresent())
		{
			CustomerLoanApplication existing=optional.get();
			
			existing.setLoanStatus(customer.getLoanStatus());
			
			return cr.save(existing);
		}else {
			return null;
		}
	}

	@Override
	public CustomerLoanApplication updateVerificationStatus(int customerID, CustomerLoanApplication customer) {
Optional<CustomerLoanApplication> op=cr.findById(customerID);
		
		if(op.isPresent())
		{
			CustomerLoanApplication cust=op.get();
			CustomerVerification verification= cust.getCustomerverification();
			 if (verification == null) {
		            verification = new CustomerVerification();
		            cust.setCustomerverification(verification);
		        }
		    verification.setVerificationDate(new Date());
			if("Verified".equalsIgnoreCase(customer.getLoanStatus()))
			{
				cust.getCustomerverification().setStatus("Sanction");
				cust.getCustomerverification().setRemarks("OK..");
				cust.getCustomerverification().setVerificationDate(new Date());
				cust.setLoanStatus("Sanctioned");
				return cr.save(customer);
			}else 
			{
				cust.getCustomerverification().setStatus("Not Sanction");
				cust.getCustomerverification().setRemarks("Poor..");
				cust.getCustomerverification().setVerificationDate(new Date());
				cust.setLoanStatus("Not Sanctioned");
				return cr.save(cust);
			}
		}
					
		else {
			throw new RuntimeException("Customer does not exist for..");
		}
		
	
	}

	}


