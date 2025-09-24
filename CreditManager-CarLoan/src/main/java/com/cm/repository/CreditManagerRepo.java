package com.cm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.model.CustomerDetails;
import com.cm.model.SanctionLetter;

@Repository
public interface CreditManagerRepo extends JpaRepository<CustomerDetails, Integer>{


	//Get All Verified Data in CreditManagerRepo
	 
	public List<CustomerDetails> findByLoanStatus(String status);

	List<CustomerDetails> findBySanctionLetter_status(String status);


	public List<CustomerDetails> findByCustomerLoanApplicationLoanStatus(String desiredStatus);

	public List<CustomerDetails> findBySanctionLetter_statusIgnoreCase(String status);


}
