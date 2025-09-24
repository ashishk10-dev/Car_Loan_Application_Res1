package com.er.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.er.model.CustomerLoanApplication;
import com.er.model.EREnquiry;
@Repository
public interface ERRepository extends JpaRepository<EREnquiry, Integer>{


 public List<EREnquiry> findByCibilStatusIgnoreCase(String enquiryStatus);

	List<EREnquiry> findByCibilStatus(String status);



	List<EREnquiry> findByEnquiryStatusIgnoreCase(String enquiryStatus);

	public CustomerLoanApplication save(CustomerLoanApplication loanData);

//	public CustomerLoanApplication save(CustomerLoanApplication loanData);


}
