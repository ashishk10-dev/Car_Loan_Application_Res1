package com.cm.service;

import java.util.List;

import java.util.ArrayList;

import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cm.model.CustomerDetails;
import com.cm.model.CustomerLoanApplication;
import com.cm.model.SanctionLetter;
import com.cm.repository.CreditManagerRepo;
import com.cm.repository.CustomerLoanApplicationRepository;

@Service
public class CreditManagerServiceImpl implements CreditManagerServiceI{

	@Autowired
	CreditManagerRepo cr;

	
	@Autowired
	CustomerLoanApplicationRepository car;
	
	
	@Override
	public CustomerDetails addData(CustomerDetails customer) {
	    int customerId = customer.getCustomerID();

	    Optional<CustomerLoanApplication> optionalLoan = car.findById(customerId);
	    if (!optionalLoan.isPresent()) {
	        throw new RuntimeException("Loan application not found for ID: " + customerId);
	    }

	    CustomerLoanApplication loan = optionalLoan.get();

	    if (cr.existsById(customerId)) {
	        
	        CustomerDetails existingCustomer = cr.findById(customerId).get();

	        existingCustomer.setCustomerName(loan.getCustomerName());
	        existingCustomer.setDateOfBirth(loan.getDateOfBirth());
	        existingCustomer.setAge(loan.getAge());
	        existingCustomer.setRequiredTenure(loan.getRequiredTenure());
	        existingCustomer.setCustomerGender(loan.getCustomerGender());
	        existingCustomer.setCustomerEmail(loan.getCustomerEmail());
	        existingCustomer.setCustomerMobileNumber(loan.getCustomerMobileNumber());
	        existingCustomer.setAdditionalMobileNumber(loan.getAdditionalMobileNumber());
//	        existingCustomer.setAmountPaidForHome(loan.getAmountPaidForHome());
	        existingCustomer.setAmountPaidForCar(loan.getAmountPaidForCar());
	        existingCustomer.setTotalLoanRequired(loan.getTotalLoanRequired());
	        existingCustomer.setLoanStatus(loan.getLoanStatus());

	        return cr.save(existingCustomer);
	    } else {
	       
	        customer.setCustomerName(loan.getCustomerName());
	        customer.setDateOfBirth(loan.getDateOfBirth());
	        customer.setAge(loan.getAge());
	        customer.setRequiredTenure(loan.getRequiredTenure());
	        customer.setCustomerGender(loan.getCustomerGender());
	        customer.setCustomerEmail(loan.getCustomerEmail());
	        customer.setCustomerMobileNumber(loan.getCustomerMobileNumber());
	        customer.setAdditionalMobileNumber(loan.getAdditionalMobileNumber());
//	        customer.setAmountPaidForHome(loan.getAmountPaidForHome());
	        customer.setAmountPaidForCar(loan.getAmountPaidForCar());
	        customer.setTotalLoanRequired(loan.getTotalLoanRequired());
	        customer.setLoanStatus(loan.getLoanStatus());

	        return cr.save(customer);
	    }
	}


	@Override
	public CustomerDetails updateSanctionStatus(int customerID, CustomerDetails customer) {
		Optional<CustomerDetails> op=cr.findById(customerID);
		if(op.isPresent())
		{
			CustomerDetails existing=op.get();
			existing.getSanctionLetter().setStatus(customer.getSanctionLetter().getStatus());
			return cr.save(existing);
		}
		else 
		{
		return null;
		}
	}


	@Override
	public List<CustomerDetails> getVerifiedCustomers() {
	    return cr.findByCustomerLoanApplicationLoanStatus("Verified");
	}



	@Override
	public List<CustomerDetails> getAcceptedData(String Status) {
		List<CustomerDetails> list = cr.findBySanctionLetter_status("Accepted");
     	return list;
	}

	@Override
	public List<CustomerDetails> getOfferedData(String status) {
		List<CustomerDetails> list = cr.findBySanctionLetter_status("Offered");
     	return list;

	}


	@Override
	public List<CustomerDetails> getAllRejectedSanctionLetters() {
		 return cr.findBySanctionLetter_status("Rejected");
	}
	


}





