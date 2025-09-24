package com.cm.service;

import java.awt.Font;
import com.lowagie.text.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cm.model.Cibil;
import com.cm.model.CustomerDetails;
import com.cm.model.CustomerLoanApplication;
import com.cm.model.EREnquiry;
import com.cm.model.SanctionLetter;
import com.cm.repository.CreditManagerRepo;
import com.cm.repository.CustomerLoanApplicationRepository;
import com.cm.repository.ERRepository;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.transaction.Transactional;

@Service
public class SanctionServiceImpl implements SanctionServiceI {
	
	@Autowired
	CreditManagerRepo cr;
	
	@Autowired
	ERRepository er;
	
	
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	@Transactional
	
	public CustomerDetails generateSactionId(int customerID, SanctionLetter sanctionLetter) {
	    Optional<CustomerDetails> optionalCustomer = cr.findById(customerID);
	    if (optionalCustomer.isEmpty()) {
	        System.out.println("Customer not found");
	        return null;
	    }

	    CustomerDetails customer = optionalCustomer.get();
	    CustomerLoanApplication application = customer.getCustomerLoanApplication();
	    if (application == null || !"Verified".equalsIgnoreCase(application.getLoanStatus())) {
	        System.out.println("Loan not verified");
	        return null;
	    }

	    Optional<EREnquiry> optionalEnquiry = er.findByCustomerCustomerID(customerID);
	    if (optionalEnquiry.isEmpty()) {
	        System.out.println("Enquiry not found");
	        return null;
	    }

	    EREnquiry enquiry = er.findByCustomerCustomerID(customerID).get();
	    Cibil cibil = enquiry.getCibil();


	      
        Integer score = cibil.getCibilScore();

        String cibilStatus = "";
        String cibilRemark = "";
        double loanRequired = sanctionLetter.getLoanRequired();
        double loanSanctioned = 0.0;
        float rateOfInterest = 0.0f;
        int tenure = 0;
        double emi = 0.0;

        if(score >= 300 && score <= 600) {
            cibilRemark = "Not Approved";
            cibilStatus = "Needs Help";
            sanctionLetter.setStatus("Rejected");
        } else if(score > 600 && score <= 700) {
            cibilRemark = "Not Approved";
            cibilStatus = "Average";
            sanctionLetter.setStatus("Rejected");
        } else if(score > 700 && score <= 760) {
            cibilRemark = "Approved";
            cibilStatus = "Good";
            loanSanctioned = loanRequired * 0.3;
            rateOfInterest = 12.0f;
            tenure = 24;
        } else if(score > 760 && score <= 800) {
            cibilRemark = "Approved";
            cibilStatus = "Very Good";
            loanSanctioned = loanRequired * 0.8;
            rateOfInterest = 11.0f;
            tenure = 18;
        } else if(score > 800 && score <= 900) {
            cibilRemark = "Approved";
            cibilStatus = "Excellent";
            loanSanctioned = loanRequired;
            rateOfInterest = 10.5f;
            tenure = 12;
        }



//	    // Update CIBIL details
//	    cibil.setStatus(cibilStatus);
//	    cibil.setCibilRemark(cibilRemark);

	    if ("Not Approved".equalsIgnoreCase(cibilRemark)) {
	        sanctionLetter.setStatus("Rejected");
	        sanctionLetter.setLoanAmountSanctioned(0.0);
	        sanctionLetter.setRateOfInterest(0.0f);
	        sanctionLetter.setLoanTenureInMonths(0);
	        sanctionLetter.setMonthlyEmiAmount(0.0);
	        sanctionLetter.setSanctionLetter(null);
	    } else {
	       
	        
	    	double monthlyInterestRate = rateOfInterest / (12 * 100);
            emi = (loanSanctioned * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenure)) /
                        (Math.pow(1 + monthlyInterestRate, tenure) - 1);

           sanctionLetter.setLoanAmountSanctioned(loanSanctioned);
           sanctionLetter.setRateOfInterest(rateOfInterest);
           sanctionLetter.setLoanTenureInMonths(tenure);
           
           sanctionLetter.setMonthlyEmiAmount(emi);
           sanctionLetter.setApplicantName(customer.getCustomerName());
           sanctionLetter.setContactDetails(customer.getCustomerMobileNumber());
           sanctionLetter.setSanctionDate(new Date());
           sanctionLetter.setStatus("Created");
           System.out.println("Tenure: " + sanctionLetter.getLoanTenureInMonths());


	    }


	    sanctionLetter.setSanctionDate(new Date());

	    // Generate PDF if approved
	    if ("Created".equalsIgnoreCase(sanctionLetter.getStatus())) {
	        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	            Document doc = new Document(PageSize.A4);
	            PdfWriter.getInstance(doc, baos);
	            doc.open();

	            // Add logo
	            try {
	                Image logo = Image.getInstance("C:\\Users\\rohan\\OneDrive\\Desktop\\ajt_logo.png");
	                logo.scalePercent(50);
	                logo.setAlignment(Element.ALIGN_RIGHT);
	                doc.add(logo);
	            } catch (Exception imgEx) {
	                System.out.println("Logo load failed: " + imgEx.getMessage());
	            }

	            // Header and greeting
	            Paragraph header = new Paragraph("ABC Finance Ltd.", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25));
	            header.setAlignment(Paragraph.ALIGN_CENTER);
	            doc.add(header);

	            doc.add(new Paragraph("\n\nDear " + customer.getCustomerName() + ",\nYour loan has been approved."));

	            // Loan details table
	            PdfPTable table = new PdfPTable(2);
	            table.setWidthPercentage(100f);
	            table.setSpacingBefore(10f);
	            table.addCell("Loan Sanctioned");
	            table.addCell("₹ " + sanctionLetter.getLoanAmountSanctioned());
	            table.addCell("Loan Tenure");
	            table.addCell(sanctionLetter.getLoanTenureInMonths()+ "months");
	            table.addCell("Interest Rate");
	            table.addCell(sanctionLetter.getRateOfInterest() + " %");
	            table.addCell("Monthly EMI");
	            table.addCell("₹ " + Math.round(sanctionLetter.getMonthlyEmiAmount()));
	            table.addCell("Sanction Date");
	            table.addCell(new SimpleDateFormat("dd MMM yyyy").format(new Date()));
	            doc.add(table);

	            doc.add(new Paragraph("\n\nThank you,\nAshish K. (Credit Manager)"));

	            doc.close();
	            sanctionLetter.setSanctionLetter(baos.toByteArray());

	        } catch (Exception pdfEx) {
	            System.out.println("PDF creation error: " + pdfEx.getMessage());
	        }
	    }

	    customer.setSanctionLetter(sanctionLetter);
	    return cr.save(customer);
	}

}




	


	



