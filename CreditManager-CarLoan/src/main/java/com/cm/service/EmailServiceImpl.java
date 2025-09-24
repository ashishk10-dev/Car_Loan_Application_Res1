package com.cm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cm.model.CustomerDetails;
import com.cm.repository.CreditManagerRepo;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailServiceI{
	@Autowired
	CreditManagerRepo cr;

	@Autowired
	JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String fromEmail;
	
	
	
	@Override
	public void sendSantionLetterMail(CustomerDetails customerDetails) {
		
MimeMessage mimemessage = sender.createMimeMessage();
		
		byte[] sanctionLetter = customerDetails.getSanctionLetter().getSanctionLetter();

		try {
			MimeMessageHelper mimemessageHelper = new MimeMessageHelper(mimemessage, true);
			mimemessageHelper.setFrom(fromEmail);
			mimemessageHelper.setTo(customerDetails.getCustomerEmail());
			mimemessageHelper.setSubject("AJT Finance Ltd. Sanction Letter");
			String text = "Dear " + customerDetails.getCustomerName()
					+ ",\n" + "\n"
					+ "This letter is to inform you that we have reviewed your request for a credit loan . We are pleased to offer you a credit loan of "
					+ customerDetails.getSanctionLetter().getLoanAmountSanctioned() + " for "
					+ customerDetails.getSanctionLetter().getLoanTenureInMonths() + ".\n" + "\n"
					+ "We are confident that you will manage your credit loan responsibly, and we look forward to your continued business.\n"
					+ "\n"
					+ "Should you have any questions about your credit loan, please do not hesitate to contact us.\n"
					+ "\n" + "Thank you for your interest in our services.";

			mimemessageHelper.setText(text);

			mimemessageHelper.addAttachment("loanSanctionLetter.pdf", new ByteArrayResource(sanctionLetter));
			sender.send(mimemessage);
			customerDetails.getSanctionLetter().setStatus("Offered");
	        cr.save(customerDetails);

		} catch (Exception e) {
			System.out.println("Email Failed to Send!!!!!!");
			e.printStackTrace();
		}
		
		
	}

		
}
