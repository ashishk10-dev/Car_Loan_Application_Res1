package com.oe.model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CustomerVerification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int verificationID;
	private Date verificationDate;
	private String status;
	private String remarks;
	public int getVerificationID() {
		return verificationID;
	}
	public void setVerificationID(int verificationID) {
		this.verificationID = verificationID;
	}
	public Date getVerificationDate() {
		return verificationDate;
	}
	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	

}
