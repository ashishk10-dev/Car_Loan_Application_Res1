package com.cm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cm.model.EREnquiry;
@Repository
public interface ERRepository extends JpaRepository<EREnquiry, Integer>{

	Optional<EREnquiry> findByCustomerCustomerID(int customerID);

}
