package com.oe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CustomerLoanApplication {

    @Id
	private int customerID;
	private String customerName;
	private String dateOfBirth;
	private int age;
	private int requiredTenure;
	private String customerGender;
	private String customerEmail;
	private double customerMobileNumber;
	private double additionalMobileNumber;
//	private double amountPaidForHome;
	@Column(name = "amount_paid_for_car") //   DB column
	@JsonProperty("amountPaidForCar")     // JSON field from frontend
	private double amountPaidForCar;
	private double totalLoanRequired;
	private String loanStatus;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private CustomerVerification customerverification;
	
	public int getCustomerID() {
		return customerID;
	}
	public CustomerVerification getCustomerverification() {
		return customerverification;
	}
	public void setCustomerverification(CustomerVerification customerverification) {
		this.customerverification = customerverification;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getRequiredTenure() {
		return requiredTenure;
	}
	public void setRequiredTenure(int requiredTenure) {
		this.requiredTenure = requiredTenure;
	}
	public String getCustomerGender() {
		return customerGender;
	}
	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public double getCustomerMobileNumber() {
		return customerMobileNumber;
	}
	public void setCustomerMobileNumber(double customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}
	public double getAdditionalMobileNumber() {
		return additionalMobileNumber;
	}
	public void setAdditionalMobileNumber(double additionalMobileNumber) {
		this.additionalMobileNumber = additionalMobileNumber;
	}
//	/////////////////////////////////////////////////
//	public double getAmountPaidForHome() {
//		return amountPaidForHome;
//	}
//	public void setAmountPaidForHome(double amountPaidForHome) {
//		this.amountPaidForHome = amountPaidForHome;
//	}
///////////////////////////////////////////////
	
	public double getAmountPaidForCar() {
		return amountPaidForCar;
	}
	public void setAmountPaidForCar(double amountPaidForCar) {
		this.amountPaidForCar = amountPaidForCar;
	}

	
	public double getTotalLoanRequired() {
		return totalLoanRequired;
	}
	public void setTotalLoanRequired(double totalLoanRequired) {
		this.totalLoanRequired = totalLoanRequired;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	
	
}
