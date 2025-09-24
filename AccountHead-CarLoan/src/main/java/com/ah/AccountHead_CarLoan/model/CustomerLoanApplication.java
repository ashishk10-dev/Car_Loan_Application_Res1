package com.ah.AccountHead_CarLoan.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private LoanDisbursement loanDisbursement;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private AccountDetails accountdetails;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Ledger> ledger;
	
	

	public AccountDetails getAccountdetails() {
		return accountdetails;
	}

	public void setAccountdetails(AccountDetails accountdetails) {
		this.accountdetails = accountdetails;
	}

	public int getCustomerID() {
		return customerID;
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

//	public double getAmountPaidForHome() {
//		return amountPaidForHome;
//	}
//
//	public void setAmountPaidForHome(double amountPaidForHome) {
//		this.amountPaidForHome = amountPaidForHome;
//	}

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

	public LoanDisbursement getLoanDisbursement() {
		return loanDisbursement;
	}

	public void setLoanDisbursement(LoanDisbursement loanDisbursement) {
		this.loanDisbursement = loanDisbursement;
	}

	public List<Ledger> getLedger() {
		return ledger;
	}

	public void setLedger(List<Ledger> ledger) {
		this.ledger = ledger;
	}

	

	
	
	
	
	
	
}
