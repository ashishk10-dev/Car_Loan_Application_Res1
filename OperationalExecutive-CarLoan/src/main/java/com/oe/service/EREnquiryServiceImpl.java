package com.oe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.oe.model.Cibil;
import com.oe.model.EREnquiry;
import com.oe.repository.EREnquiryRepository;


@Service
public class EREnquiryServiceImpl implements EREnquiryServiceI{
	@Autowired
	EREnquiryRepository er;
	
	@Autowired
	RestTemplate rt;
	
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String SEND_MAIL;

       String cibilApi="http://localhost:8083/getcibil";



	@Override
	public List<EREnquiry> getCibil() {
		List<EREnquiry> pendingList=er.findByEnquiryStatus("Cibil Pending");
		
		List<EREnquiry> updatedList= new ArrayList<>();
		for(EREnquiry enquiry: pendingList)
		{
			int score=rt.getForObject(cibilApi, Integer.class);
			
			
			Cibil cibil=enquiry.getCibil();
			
			if (cibil == null) {
			    cibil = new Cibil(); 
			}
			
			cibil.setCibilScore(score);
			cibil.setCibilScoreDateTime(new Date());
			
			
			if(score >=300 && score<=600)
				{
					cibil.setStatus("Needs Help");
					cibil.setCibilRemark("Not Approved");
				}else if(score>600 && score<=700)
				{
					cibil.setStatus("Average");
					cibil.setCibilRemark("Not Approved");
				}else if(score>700 && score<=760)
				{
					cibil.setStatus("Good");
					cibil.setCibilRemark("Approved");
				}else if(score>760 && score<=800)
				{
					cibil.setStatus("Very Good");
					cibil.setCibilRemark("Approved");
				}else if(score>800 && score<=900)
				{
					cibil.setStatus("Excellent");
					cibil.setCibilRemark("Approved");
				}
				enquiry.setCibil(cibil);
				if ("Approved".equalsIgnoreCase(cibil.getCibilRemark())) {
				    enquiry.setEnquiryStatus("Approved");
				} else {
				    enquiry.setEnquiryStatus("Rejected");
				}
				SimpleMailMessage simple= new SimpleMailMessage();
				simple.setTo(enquiry.getEmail());
				simple.setFrom(SEND_MAIL);
				simple.setSubject("Loan Enquiry CIBIL Status.");
				String message;

				if ("Approved".equalsIgnoreCase(enquiry.getCibil().getCibilRemark())) {
				    message = "Dear " + enquiry.getFirstName() + " " + enquiry.getLastName() + ",\n\n" +
				            "We are delighted to inform you that your application for a Car Loan with XYZ Bank has been approved.\n\n" +
				            "After a thorough review of your financial documents and eligibility, we are happy to extend the following loan offer:\n\n" +
				            "Approved Loan Summary\n" +
				            "• CIBIL Score: " + enquiry.getCibil().getCibilScore() + "\n" +
				            "• Status: " + enquiry.getCibil().getStatus() + "\n\n" +
				            "To proceed, please confirm your acceptance of the terms by logging into your online banking portal or visiting your nearest branch. " +
				            "Once the agreement is signed, funds will be disbursed to your account within 10 working days.\n\n" +
				            "Your Enquiry Status: " + enquiry.getEnquiryStatus();
				} else {
				    message = "Hi " + enquiry.getFirstName() + " " + enquiry.getLastName() + ",\n\n" +
				            "Your CIBIL score has been processed.\n" +
				            "CIBIL Score: " + enquiry.getCibil().getCibilScore() + "\n" +
				            "Status: " + enquiry.getCibil().getStatus() + "\n" +
				            "Remark: " + enquiry.getCibil().getCibilRemark() + "\n\n" +
				            "Unfortunately, your current CIBIL score does not meet the required criteria for loan approval at this time.\n\n" +
				            "**Tips to Improve Your CIBIL Score:**\n" +
				            "• Pay your EMIs and credit card bills on time\n" +
				            "• Keep credit utilization low (preferably below 30%)\n" +
				            "• Avoid applying for multiple loans or credit cards simultaneously\n" +
				            "• Regularly monitor your credit report for errors\n\n" +
				            "Your Enquiry Status: " + enquiry.getEnquiryStatus() + "\n\n" +
				            "We encourage you to improve your score and reapply in the future.";
				}

				simple.setText(message);
				simple.setCc(enquiry.getEmail());
				mailSender.send(simple);
				er.save(enquiry);
				
				updatedList.add(enquiry);
		}
		return updatedList;
	}



	@Override
	public EREnquiry getSingleCibil(int customerID) {
		EREnquiry pending=er.findById(customerID).get();
		

		
			int score=rt.getForObject(cibilApi, Integer.class);
			
			
			Cibil cibil=pending.getCibil();
			
			if (cibil == null) {
			    cibil = new Cibil(); 
			}
			
			cibil.setCibilScore(score);
			cibil.setCibilScoreDateTime(new Date());
			
			
			if(score >=300 && score<=600)
				{
					cibil.setStatus("Needs Help");
					cibil.setCibilRemark("Not Approved");
				}else if(score>600 && score<=700)
				{
					cibil.setStatus("Average");
					cibil.setCibilRemark("Not Approved");
				}else if(score>700 && score<=760)
				{
					cibil.setStatus("Good");
					cibil.setCibilRemark("Approved");
				}else if(score>760 && score<=800)
				{
					cibil.setStatus("Very Good");
					cibil.setCibilRemark("Approved");
				}else if(score>800 && score<=900)
				{
					cibil.setStatus("Excellent");
					cibil.setCibilRemark("Approved");
				}
				pending.setCibil(cibil);
				if("Approved".equalsIgnoreCase(cibil.getCibilRemark())) {
				    pending.setEnquiryStatus("Approved");
				} else {
				    pending.setEnquiryStatus("Rejected");
				}
				
				SimpleMailMessage simple= new SimpleMailMessage();
				simple.setTo(pending.getEmail());
				simple.setFrom(SEND_MAIL);
				simple.setSubject("Loan Enquiry CIBIL Status.");
				String message;

				if ("Approved".equalsIgnoreCase(pending.getCibil().getCibilRemark())) {
				    message = "Dear " + pending.getFirstName() + " " + pending.getLastName() + ",\n\n" +
				            "We are delighted to inform you that your application for a Car Loan with XYZ Bank has been approved.\n\n" +
				            "After a thorough review of your financial documents and eligibility, we are happy to extend the following loan offer:\n\n" +
				            "Approved Loan Summary\n" +
				            "• CIBIL Score: " + pending.getCibil().getCibilScore() + "\n" +
				            "• Status: " + pending.getCibil().getStatus() + "\n\n" +
				            "To proceed, please confirm your acceptance of the terms by logging into your online banking portal or visiting your nearest branch. " +
				            "Once the agreement is signed, funds will be disbursed to your account within 10 working days.\n\n" +
				            "Your Enquiry Status: " + pending.getEnquiryStatus();
				} else {
				    message = "Hi " + pending.getFirstName() + " " + pending.getLastName() + ",\n\n" +
				            "Your CIBIL score has been processed.\n" +
				            "CIBIL Score: " + pending.getCibil().getCibilScore() + "\n" +
				            "Status: " + pending.getCibil().getStatus() + "\n" +
				            "Remark: " + pending.getCibil().getCibilRemark() + "\n\n" +
				            "Unfortunately, your current CIBIL score does not meet the required criteria for loan approval at this time.\n\n" +
				            "**Tips to Improve Your CIBIL Score:**\n" +
				            "• Pay your EMIs and credit card bills on time\n" +
				            "• Keep credit utilization low (preferably below 30%)\n" +
				            "• Avoid applying for multiple loans or credit cards simultaneously\n" +
				            "• Regularly monitor your credit report for errors\n\n" +
				            "Your Enquiry Status: " + pending.getEnquiryStatus() + "\n\n" +
				            "We encourage you to improve your score and reapply in the future.";
				}

				simple.setText(message);
				
					simple.setCc(pending.getEmail());
				
				mailSender.send(simple);
				
				er.save(pending);
				return pending;
				
		}
	
	}



	
      



	




	


