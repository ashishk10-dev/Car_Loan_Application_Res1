package com.ah.AccountHead_CarLoan.service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ah.AccountHead_CarLoan.model.CustomerDetails;
import com.ah.AccountHead_CarLoan.model.CustomerLoanApplication;
import com.ah.AccountHead_CarLoan.model.Ledger;
import com.ah.AccountHead_CarLoan.model.LoanDisbursement;
import com.ah.AccountHead_CarLoan.model.SanctionLetter;
import com.ah.AccountHead_CarLoan.repository.AccountHeadRepo;
import com.ah.AccountHead_CarLoan.repository.CustomerLoanApplicationRepo;


@Service
public class AccountHeadServiceimpl implements AccountHeadServiceI{
     @Autowired
	AccountHeadRepo  ar;
    
     @Autowired
  CustomerLoanApplicationRepo car;
     
     
     @Autowired
 	private JavaMailSender mailSender;
     
     @Value("${spring.mail.username}")
 	private String SEND_MAIL;

	@Override
	public List<CustomerDetails> getAllAcceptedData() {
		
		List<CustomerDetails> list= ar.findBySanctionLetterStatus("Accepted");
		return list;
		
	}



	

	  

	    @Override
	    public LoanDisbursement addData(LoanDisbursement loandis, int customerId) {

	       
	        Optional<CustomerLoanApplication> optionalCustomer = car.findById(customerId);
	        if (optionalCustomer.isEmpty()) {
	            throw new RuntimeException("CustomerLoanApplication not found with ID: " + customerId);
	        }
	        CustomerLoanApplication cla = optionalCustomer.get();

	        
	        Optional<CustomerDetails> optionalDetails = ar.findById(customerId);
	        if (optionalDetails.isEmpty()) {
	            throw new RuntimeException("CustomerDetails not found with ID: " + customerId);
	        }

	        SanctionLetter sanctionLetter = optionalDetails.get().getSanctionLetter();
	        if (sanctionLetter == null) {
	            throw new RuntimeException("SanctionLetter not found for Customer ID: " + customerId);
	        }

	        
	        LoanDisbursement loandis1 = new LoanDisbursement();
	        loandis1.setAgreementDate(new Date());
	        loandis1.setAmountPayType("Online");
	        loandis1.setTotalAmount(sanctionLetter.getLoanAmountSanctioned());
	        loandis1.setBankName("XYZ Bank");
	        loandis1.setAccountNumber(loandis.getAccountNumber());
	        loandis1.setIfscCode("BKID0000FG");
	        loandis1.setAccountType(loandis.getAccountType());
	        loandis1.setTransferAmount(loandis.getTransferAmount());
	        loandis1.setPaymentStatus("Disburse");

	       
	        cla.setLoanDisbursement(loandis1);
	        car.save(cla);

	        return loandis1;
	    }
	




	@Override
	public LoanDisbursement updateStatus(int customerId) {
		
		Optional<CustomerLoanApplication> opLoan=car.findById(customerId);
		if(opLoan.isPresent())
		{
			CustomerLoanApplication customer=opLoan.get();
			LoanDisbursement existing=customer.getLoanDisbursement();
			
			if(existing==null)
			{
				 throw new RuntimeException("No LoanDisbursement found for Customer ID: " + customerId);
			}
			
			existing.setPaymentStatus("Disbursed");
		     existing.setAmountPaidDate(new Date());
		     
		     customer.setLoanDisbursement(existing);
		     
		     car.save(customer);
		     
		     return existing;
		}else {
			throw new RuntimeException("CustomerLoanApplication not found with ID: " + customerId);
	    }
		}







	@Override
	public Ledger addLedger(int customerId, Ledger ledger) {
	
		Optional<CustomerLoanApplication> opLoan= car.findById(customerId);
		
		 if (opLoan.isEmpty()) {
	            throw new RuntimeException("CustomerLoanApplication not found with ID: " + customerId);
	        }
		 
		 CustomerLoanApplication cla = opLoan.get();
		 LoanDisbursement existingdis=cla.getLoanDisbursement();
		 
		 Optional<CustomerDetails> optionalDetails = ar.findById(customerId);
	        if (optionalDetails.isEmpty()) {
	            throw new RuntimeException("CustomerDetails not found with ID: " + customerId);
	        }

	        SanctionLetter sanctionLetter = optionalDetails.get().getSanctionLetter();
	        if (sanctionLetter == null) {
	            throw new RuntimeException("SanctionLetter not found for Customer ID: " + customerId);
	        }
	        List<Ledger> ledgerList = cla.getLedger();
	        if (ledgerList == null) {
	            ledgerList = new ArrayList<>();
	        }
		 
		 //Calculating interest
		 double principal = sanctionLetter.getLoanAmountSanctioned();
		    int tenure = sanctionLetter.getLoanTenureInMonths();
		    double annualInterestRate = sanctionLetter.getRateOfInterest(); 

		    double interest = (principal * annualInterestRate * tenure) / (12 * 100);
		    double totalPayable = principal + interest;
		    
		    Ledger lg = new Ledger();
		    //For next Date
		    
		    Date ledgerCreatedDate = new Date();
		    lg.setLedgerCreatedDate(ledgerCreatedDate);

		    // Convert to LocalDate
		    LocalDate createdLocalDate = ledgerCreatedDate.toInstant()
		        .atZone(ZoneId.systemDefault())
		        .toLocalDate();

		    // Add one month
		    LocalDate nextEmiStartLocalDate = createdLocalDate.plusMonths(1);

		    // Convert back to java.util.Date
		    Date nextEmiStartDate = Date.from(nextEmiStartLocalDate
		        .atStartOfDay(ZoneId.systemDefault())
		        .toInstant());
		    
		 // Calculate loan end date (after full tenure)
		    int tenure1 = sanctionLetter.getLoanTenureInMonths();
		    LocalDate loanEndLocalDate = createdLocalDate.plusMonths(tenure1);
		    Date loanEndDate = Date.from(loanEndLocalDate
		        .atStartOfDay(ZoneId.systemDefault())
		        .toInstant());
		    lg.setLoanEndDate(loanEndDate);

		    

		 
		 if("Disbursed".equalsIgnoreCase(existingdis.getPaymentStatus()))
		 {
			  lg.setLedgerCreatedDate(new Date());
			  lg.setTotalLoanAmount(sanctionLetter.getLoanAmountSanctioned());
			  lg.setTenure(sanctionLetter.getLoanTenureInMonths());
			  lg.setPayableAmountWithInterest(totalPayable);
			  lg.setMonthlyEMI(totalPayable/tenure1);
			  lg.setAmountPaidtillDate(0);
			  lg.setRemainingAmount(totalPayable);
			  lg.setNextEmiDateStart(nextEmiStartDate);
			  lg.setNextEmiDateEnd(nextEmiStartDate);
			  lg.setDefaultCount(0);
			  lg.setPreviousEmiStatus("Not yet Started");
			  lg.setCurrentMonthEmiStatus("Unpaid");
			  lg.setLoanEndDate(loanEndDate);
			  lg.setLedgerLoanStatus("Open");
			 ledgerList.add(lg);
			 cla.setLedger(ledgerList);
			 car.save(cla);
				return lg;
		 }
		
		 throw new RuntimeException("Loan is not disbursed yet for customer ID: " + customerId);
	}







	@Override
	public Ledger updatePay(int customerId, double amountPaid) {
	    Optional<CustomerLoanApplication> opCustomer = car.findById(customerId);
	    if (opCustomer.isEmpty()) {
	        throw new RuntimeException("Customer not found with ID: " + customerId);
	    }

	    CustomerLoanApplication customer = opCustomer.get();
	    List<Ledger> ledgers = customer.getLedger();

	    if (ledgers == null ) {
	        throw new RuntimeException("No ledgers found for customer ID: " + customerId);
	    }

	    Ledger currentLedger = null;
	    Date latestDate = null;

	    for (Ledger ledger : ledgers) {
	        if ("Open".equalsIgnoreCase(ledger.getLedgerLoanStatus())) {
	            if (latestDate == null || ledger.getLedgerCreatedDate().after(latestDate)) {
	                latestDate = ledger.getLedgerCreatedDate();
	                currentLedger = ledger;
	            }
	        }
	    }

	    if (currentLedger == null) {
	        throw new RuntimeException("No open ledger found for customer");
	    }


//	    if (amountPaid <= 0) {
//	        throw new IllegalArgumentException("Amount paid must be greater than zero.");
//	    }
	    double updatedAmountPaid = currentLedger.getAmountPaidtillDate() + amountPaid;
	    double updatedRemaining = currentLedger.getRemainingAmount() - amountPaid;
	    // Update current ledger
//	    currentLedger.setAmountPaidtillDate(updatedAmountPaid);
//	    currentLedger.setRemainingAmount(updatedRemaining);

	    if (amountPaid >= currentLedger.getMonthlyEMI()) {
	        currentLedger.setCurrentMonthEmiStatus("Paid");
	    } else if(amountPaid<currentLedger.getMonthlyEMI() && amountPaid!=0) {
	        currentLedger.setCurrentMonthEmiStatus("Partially Paid");
	    }else
	    {
	    	  currentLedger.setCurrentMonthEmiStatus("Unpaid");
	    }

	    Ledger nextLedger = null;
	    if (currentLedger.getRemainingAmount() > 0) {
	        nextLedger = new Ledger();
	       
	        nextLedger.setLedgerCreatedDate(new Date());
	        nextLedger.setTotalLoanAmount(currentLedger.getTotalLoanAmount());
	        nextLedger.setTenure(currentLedger.getTenure());
	        nextLedger.setMonthlyEMI(currentLedger.getMonthlyEMI());

	        nextLedger.setAmountPaidtillDate(updatedAmountPaid);
	        nextLedger.setRemainingAmount(updatedRemaining);

//	        nextLedger.setRemainingAmount(currentLedger.getRemainingAmount());
	        nextLedger.setPayableAmountWithInterest(currentLedger.getPayableAmountWithInterest());

	        // Increment default count
	        int newDefaultCount = 0;
	        if (!"Paid".equalsIgnoreCase(currentLedger.getCurrentMonthEmiStatus()) 
	        		&& !"Partially Paid".equalsIgnoreCase(currentLedger.getCurrentMonthEmiStatus())) {
	            newDefaultCount = currentLedger.getDefaultCount() + 1;
	        }

	        nextLedger.setDefaultCount(newDefaultCount);

	        //E-mail
	        if(newDefaultCount==3)
	        {
	        SimpleMailMessage simple= new SimpleMailMessage();
			simple.setTo("rohanranga234@gmail.com");
			simple.setFrom(SEND_MAIL);
			simple.setSubject("");
			String message;

			
			    message = "Dear " + customer.getCustomerName() + ",\n\n"
		                + "You have missed 3 consecutive EMI payments. Please clear your dues immediately to avoid penalties or legal action.\n\n"
		               
		                + "Outstanding Amount: ₹" + currentLedger.getRemainingAmount() + "\n\n"
		                + "Regards,\nLoan Management Team";
	    

			simple.setText(message);
			
			mailSender.send(simple);
	        
	        
	        }
	        
	        nextLedger.setPreviousEmiStatus(currentLedger.getCurrentMonthEmiStatus());
	        nextLedger.setCurrentMonthEmiStatus("Unpaid");
	        nextLedger.setLedgerLoanStatus("Open");

	        // Set next EMI dates
	        LocalDate prevEmiDate = currentLedger.getNextEmiDateStart().toInstant()
	                .atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate nextEmiDate = prevEmiDate.plusMonths(1);
	        Date nextEmiDateStart = Date.from(nextEmiDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	        nextLedger.setNextEmiDateStart(nextEmiDateStart);
	        nextLedger.setNextEmiDateEnd(nextEmiDateStart);
	        nextLedger.setLoanEndDate(currentLedger.getLoanEndDate());

	        ledgers.add(nextLedger);
	    } else {
	        currentLedger.setLedgerLoanStatus("Closed");
	    }

	    customer.setLedger(ledgers);
	    car.save(customer);

	   return nextLedger;
	}

		
	}


