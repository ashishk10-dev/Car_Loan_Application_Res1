package com.oe.service;

import java.util.List;

import com.oe.model.EREnquiry;


public interface EREnquiryServiceI {


    public List<EREnquiry> getCibil();

	public EREnquiry getSingleCibil(int customerID);

}
