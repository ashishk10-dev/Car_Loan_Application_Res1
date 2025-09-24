package com.oe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oe.model.CustomerLoanApplication;
@Repository
public interface CustomerLoanApplicationRepo extends JpaRepository<CustomerLoanApplication, Integer> {

}
