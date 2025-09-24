package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.model.CustomerLoanApplication;

@Repository
public interface CustomerLoanApplicationRepository extends JpaRepository<CustomerLoanApplication, Integer>{

}
