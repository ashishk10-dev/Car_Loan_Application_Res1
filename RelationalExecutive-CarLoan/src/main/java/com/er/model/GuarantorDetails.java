package com.er.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@CrossOrigin("*")
@Entity
public class GuarantorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int guarantorId;
	private String guarantorName;
	private String guarantorDateOfBirth;
	private String guarantorRelationshipWithCustomer;
	private long guarantorMobileNumber;
	private long guarantorAdharCardNo;
	private String guarantorMortgageDetails;
	private String guarantorJobDetails;
	private String guarantorLocalAddress;
	private String guarantorPermanentAddress;
	public int getGuarantorId() {
		return guarantorId;
	}
	public void setGuarantorId(int guarantorId) {
		this.guarantorId = guarantorId;
	}
	public String getGuarantorName() {
		return guarantorName;
	}
	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}
	public String getGuarantorDateOfBirth() {
		return guarantorDateOfBirth;
	}
	public void setGuarantorDateOfBirth(String guarantorDateOfBirth) {
		this.guarantorDateOfBirth = guarantorDateOfBirth;
	}
	public String getGuarantorRelationshipWithCustomer() {
		return guarantorRelationshipWithCustomer;
	}
	public void setGuarantorRelationshipWithCustomer(String guarantorRelationshipWithCustomer) {
		this.guarantorRelationshipWithCustomer = guarantorRelationshipWithCustomer;
	}
	public long getGuarantorMobileNumber() {
		return guarantorMobileNumber;
	}
	public void setGuarantorMobileNumber(long guarantorMobileNumber) {
		this.guarantorMobileNumber = guarantorMobileNumber;
	}
	public long getGuarantorAdharCardNo() {
		return guarantorAdharCardNo;
	}
	public void setGuarantorAdharCardNo(long guarantorAdharCardNo) {
		this.guarantorAdharCardNo = guarantorAdharCardNo;
	}
	public String getGuarantorMortgageDetails() {
		return guarantorMortgageDetails;
	}
	public void setGuarantorMortgageDetails(String guarantorMortgageDetails) {
		this.guarantorMortgageDetails = guarantorMortgageDetails;
	}
	public String getGuarantorJobDetails() {
		return guarantorJobDetails;
	}
	public void setGuarantorJobDetails(String guarantorJobDetails) {
		this.guarantorJobDetails = guarantorJobDetails;
	}
	public String getGuarantorLocalAddress() {
		return guarantorLocalAddress;
	}
	public void setGuarantorLocalAddress(String guarantorLocalAddress) {
		this.guarantorLocalAddress = guarantorLocalAddress;
	}
	public String getGuarantorPermanentAddress() {
		return guarantorPermanentAddress;
	}
	public void setGuarantorPermanentAddress(String guarantorPermanentAddress) {
		this.guarantorPermanentAddress = guarantorPermanentAddress;
	}
	
	

}
