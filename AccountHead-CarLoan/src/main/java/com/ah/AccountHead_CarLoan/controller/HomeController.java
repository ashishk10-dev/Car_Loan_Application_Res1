package com.ah.AccountHead_CarLoan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ah.AccountHead_CarLoan.model.CustomerDetails;
import com.ah.AccountHead_CarLoan.model.Ledger;
import com.ah.AccountHead_CarLoan.model.LoanDisbursement;
import com.ah.AccountHead_CarLoan.service.AccountHeadServiceI;

@CrossOrigin("*")
@RestController
public class HomeController {
	@Autowired
	AccountHeadServiceI asi;
	
	@GetMapping("/getAllAcceptedData")
	public ResponseEntity<List<CustomerDetails>> getAllAcceptedData()
	{
		List<CustomerDetails> list= asi.getAllAcceptedData();
		return new ResponseEntity<List<CustomerDetails>>(list,HttpStatus.OK);
	}
	
	
	@PostMapping("/addLoanDisData/{customerId}")
	public ResponseEntity<LoanDisbursement> addData(@PathVariable int customerId,@RequestBody LoanDisbursement loandis)
	{
		LoanDisbursement led= asi.addData(loandis,customerId);
		return new ResponseEntity<LoanDisbursement>(led,HttpStatus.CREATED);
	}

	
	@PutMapping("/paybutton/{customerId}")
	public ResponseEntity<LoanDisbursement> updateStatus(@PathVariable int customerId)
	{
	   	LoanDisbursement ld= asi.updateStatus(customerId);
	   	return new ResponseEntity<LoanDisbursement>(ld,HttpStatus.OK);
	}
	
	@PostMapping("/addLedger/{customerId}")
	public ResponseEntity<Ledger> addLedgerData(@PathVariable int customerId,@RequestBody Ledger ledger)
	
	{
		Ledger lg= asi.addLedger(customerId,ledger);
		return new ResponseEntity<Ledger>(lg,HttpStatus.CREATED);
	}
	
	@PutMapping("/paybuttonLedger/{customerId}")
	public ResponseEntity<Ledger> updatePay(@PathVariable int customerId,
	                                        @RequestParam double amountPaid) {
	    Ledger updatedLedger =asi.updatePay(customerId, amountPaid);
	    return new ResponseEntity<>(updatedLedger, HttpStatus.OK);
	}
}
