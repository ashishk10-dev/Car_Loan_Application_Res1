package com.oe.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oe.model.EREnquiry;


@Repository
public interface EREnquiryRepository extends JpaRepository<EREnquiry, Integer>{


	List<EREnquiry> findByEnquiryStatus(String enquiryStatus);

}
