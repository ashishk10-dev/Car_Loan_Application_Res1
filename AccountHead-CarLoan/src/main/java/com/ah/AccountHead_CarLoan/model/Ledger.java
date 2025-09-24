package com.ah.AccountHead_CarLoan.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ledger {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ledgerId;
	private Date ledgerCreatedDate;
	private double totalLoanAmount;
	private double payableAmountWithInterest;
	private int tenure;
	private double monthlyEMI;
	private double amountPaidtillDate;
	private double remainingAmount;
	private Date nextEmiDateStart;
	private Date nextEmiDateEnd;
	private int defaultCount;
	private String previousEmiStatus;
	private String currentMonthEmiStatus;
	private Date loanEndDate;
	private String ledgerLoanStatus;
	public int getLedgerId() {
		return ledgerId;
	}
	public void setLedgerId(int ledgerId) {
		this.ledgerId = ledgerId;
	}
	public Date getLedgerCreatedDate() {
		return ledgerCreatedDate;
	}
	public void setLedgerCreatedDate(Date ledgerCreatedDate) {
		this.ledgerCreatedDate = ledgerCreatedDate;
	}
	public double getTotalLoanAmount() {
		return totalLoanAmount;
	}
	public void setTotalLoanAmount(double totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}
	public double getPayableAmountWithInterest() {
		return payableAmountWithInterest;
	}
	public void setPayableAmountWithInterest(double payableAmountWithInterest) {
		this.payableAmountWithInterest = payableAmountWithInterest;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public double getMonthlyEMI() {
		return monthlyEMI;
	}
	public void setMonthlyEMI(double monthlyEMI) {
		this.monthlyEMI = monthlyEMI;
	}
	public double getAmountPaidtillDate() {
		return amountPaidtillDate;
	}
	public void setAmountPaidtillDate(double amountPaidtillDate) {
		this.amountPaidtillDate = amountPaidtillDate;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public Date getNextEmiDateStart() {
		return nextEmiDateStart;
	}
	public void setNextEmiDateStart(Date nextEmiDateStart) {
		this.nextEmiDateStart = nextEmiDateStart;
	}
	public Date getNextEmiDateEnd() {
		return nextEmiDateEnd;
	}
	public void setNextEmiDateEnd(Date nextEmiDateEnd) {
		this.nextEmiDateEnd = nextEmiDateEnd;
	}
	public int getDefaultCount() {
		return defaultCount;
	}
	public void setDefaultCount(int defaultCount) {
		this.defaultCount = defaultCount;
	}
	public String getPreviousEmiStatus() {
		return previousEmiStatus;
	}
	public void setPreviousEmiStatus(String previousEmiStatus) {
		this.previousEmiStatus = previousEmiStatus;
	}
	public String getCurrentMonthEmiStatus() {
		return currentMonthEmiStatus;
	}
	public void setCurrentMonthEmiStatus(String currentMonthEmiStatus) {
		this.currentMonthEmiStatus = currentMonthEmiStatus;
	}
	public Date getLoanEndDate() {
		return loanEndDate;
	}
	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}
	public String getLedgerLoanStatus() {
		return ledgerLoanStatus;
	}
	public void setLedgerLoanStatus(String ledgerLoanStatus) {
		this.ledgerLoanStatus = ledgerLoanStatus;
	}
	
	
	
	
	

}
