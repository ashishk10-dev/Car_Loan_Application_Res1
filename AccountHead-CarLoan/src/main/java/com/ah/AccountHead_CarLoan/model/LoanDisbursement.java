package com.ah.AccountHead_CarLoan.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class LoanDisbursement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int agreementId;
	
	private Date agreementDate;
	private String amountPayType;
	private double totalAmount;
	private String bankName;
	private long accountNumber;
	private String ifscCode;
	private String accountType;
	private double transferAmount;
	private String paymentStatus;
	private Date amountPaidDate;
	public int getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}
	
	public Date getAgreementDate() {
		return agreementDate;
	}
	public void setAgreementDate(Date agreementDate) {
		this.agreementDate = agreementDate;
	}
	public String getAmountPayType() {
		return amountPayType;
	}
	public void setAmountPayType(String amountPayType) {
		this.amountPayType = amountPayType;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getAmountPaidDate() {
		return amountPaidDate;
	}
	public void setAmountPaidDate(Date amountPaidDate) {
		this.amountPaidDate = amountPaidDate;
	}
	
	
	
	
	
	

}
