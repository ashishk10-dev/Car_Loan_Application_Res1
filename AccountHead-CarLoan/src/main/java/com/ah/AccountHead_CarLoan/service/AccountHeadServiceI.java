package com.ah.AccountHead_CarLoan.service;

import java.util.List;

import com.ah.AccountHead_CarLoan.model.CustomerDetails;
import com.ah.AccountHead_CarLoan.model.Ledger;
import com.ah.AccountHead_CarLoan.model.LoanDisbursement;


public interface AccountHeadServiceI {

	public List<CustomerDetails> getAllAcceptedData();

	public LoanDisbursement addData(LoanDisbursement loandis,int customerId);

	public LoanDisbursement updateStatus(int customerId);

	public Ledger addLedger(int customerId, Ledger ledger);

	public Ledger updatePay(int customerId, double amountPaid);



	

}
