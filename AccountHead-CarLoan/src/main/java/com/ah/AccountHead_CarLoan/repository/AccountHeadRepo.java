package com.ah.AccountHead_CarLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ah.AccountHead_CarLoan.model.CustomerDetails;
@Repository
public interface AccountHeadRepo extends JpaRepository<CustomerDetails, Integer> {

	 List<CustomerDetails> findBySanctionLetterStatus(String status);
	 
}
