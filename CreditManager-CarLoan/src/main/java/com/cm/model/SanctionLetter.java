package com.cm.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class SanctionLetter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sanctionId;
	private Date sanctionDate;
	private String applicantName;
	private double contactDetails;
	private double loanRequired;
	private double loanAmountSanctioned;
	private String interestType;
	private float rateOfInterest;
	private int loanTenureInMonths;
	private double monthlyEmiAmount;
	private String modeOfPayment;
	private String remarks;
	private String termsCondition;
	private String status;
	
	@Lob
	@Column(length=999999999)
	private byte[] sanctionLetter;
	public int getSanctionId() {
		return sanctionId;
	}
	public void setSanctionId(int sanctionId) {
		this.sanctionId = sanctionId;
	}
	public Date getSanctionDate() {
		return sanctionDate;
	}
	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public double getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(double contactDetails) {
		this.contactDetails = contactDetails;
	}
	public double getLoanRequired() {
		return loanRequired;
	}
	public void setLoanRequired(double loanRequired) {
		this.loanRequired = loanRequired;
	}
	public double getLoanAmountSanctioned() {
		return loanAmountSanctioned;
	}
	public void setLoanAmountSanctioned(double loanAmountSanctioned) {
		this.loanAmountSanctioned = loanAmountSanctioned;
	}
	public String getInterestType() {
		return interestType;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	public float getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(float rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public int getLoanTenureInMonths() {
		return loanTenureInMonths;
	}
	public void setLoanTenureInMonths(int loanTenureInMonths) {
		this.loanTenureInMonths = loanTenureInMonths;
	}
	public double getMonthlyEmiAmount() {
		return monthlyEmiAmount;
	}
	public void setMonthlyEmiAmount(double monthlyEmiAmount) {
		this.monthlyEmiAmount = monthlyEmiAmount;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTermsCondition() {
		return termsCondition;
	}
	public void setTermsCondition(String termsCondition) {
		this.termsCondition = termsCondition;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {

		this.status = status;

	}
	public byte[] getSanctionLetter() {
		return sanctionLetter;
	}
	public void setSanctionLetter(byte[] sanctionLetter) {
		this.sanctionLetter = sanctionLetter;
	}

}
