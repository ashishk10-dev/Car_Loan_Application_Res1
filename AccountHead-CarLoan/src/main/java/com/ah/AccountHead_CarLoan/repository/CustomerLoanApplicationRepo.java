package com.ah.AccountHead_CarLoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ah.AccountHead_CarLoan.model.CustomerLoanApplication;

@Repository
public interface CustomerLoanApplicationRepo extends JpaRepository<CustomerLoanApplication, Integer>{

	

}
