package com.er.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.er.model.CustomerLoanApplication;

@Repository
public interface CustomerLoanApplicationRepo extends JpaRepository<CustomerLoanApplication, Integer>{

	public List<CustomerLoanApplication> findByLoanStatusIgnoreCase(String loanStatus);


}
