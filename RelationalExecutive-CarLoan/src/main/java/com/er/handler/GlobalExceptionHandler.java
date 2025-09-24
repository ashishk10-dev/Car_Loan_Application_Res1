package com.er.handler;

import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.er.dto.EREnquiryResponse;
import com.er.exceptions.AgeException;
import com.er.exceptions.InvalidEmailException;
import com.er.exceptions.InvalidLastNameException;
import com.er.exceptions.InvalidMobileNumberException;
import com.er.exceptions.InvalidNameException;
import com.er.exceptions.InvalidPanCardException;

@RestControllerAdvice
public class GlobalExceptionHandler {

//FirstName
	@ExceptionHandler(InvalidNameException.class)
	public ResponseEntity<EREnquiryResponse> handleInvalidFirstNameException(InvalidNameException ivn)
	{ 
		String message=ivn.getMessage();
		EREnquiryResponse responce=new EREnquiryResponse();
		responce.setMsg(message);
		responce.setTimestamp(new Date());
		
		return new ResponseEntity<EREnquiryResponse>(responce,HttpStatus.BAD_REQUEST);		
	}
//Lastname
	@ExceptionHandler(InvalidLastNameException.class)
	public ResponseEntity<EREnquiryResponse> handleInvalidLastNameException(InvalidLastNameException ivn)
	{ 
		String message=ivn.getMessage();
		EREnquiryResponse responce=new EREnquiryResponse();
		responce.setMsg(message);
		responce.setTimestamp(new Date());
		
		return new ResponseEntity<EREnquiryResponse>(responce,HttpStatus.BAD_REQUEST);		
	}
//Age
	@ExceptionHandler(AgeException.class)
	public ResponseEntity<EREnquiryResponse> handleInvalidNameException(AgeException ae)
	{ 
		String message=ae.getMessage();
		EREnquiryResponse responce=new EREnquiryResponse();
		responce.setMsg(message);
		responce.setTimestamp(new Date());
		
		return new ResponseEntity<EREnquiryResponse>(responce,HttpStatus.BAD_REQUEST);		
	}
//Mobile Number
	@ExceptionHandler(InvalidMobileNumberException.class)
	public ResponseEntity<EREnquiryResponse> handleInvalidMobileNumberException(InvalidMobileNumberException ivm)
	{ 
		String message=ivm.getMessage();
		EREnquiryResponse responce=new EREnquiryResponse();
		responce.setMsg(message);
		responce.setTimestamp(new Date());
		
		return new ResponseEntity<EREnquiryResponse>(responce,HttpStatus.BAD_REQUEST);		
	}
	
//Email
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<EREnquiryResponse> handleInvalidEmailException(InvalidEmailException ie)
	{ 
		String message=ie.getMessage();
		EREnquiryResponse responce=new EREnquiryResponse();
		responce.setMsg(message);
		responce.setTimestamp(new Date());
		return new ResponseEntity<EREnquiryResponse>(responce,HttpStatus.BAD_REQUEST);		
	}
	
//PanCard
	@ExceptionHandler(InvalidPanCardException.class)
	public ResponseEntity<EREnquiryResponse> handleInvalidPanCardException(InvalidPanCardException ipn)
	{ 
		String message=ipn.getMessage();
		EREnquiryResponse responce=new EREnquiryResponse();
		responce.setMsg(message);
		responce.setTimestamp(new Date());
		return new ResponseEntity<EREnquiryResponse>(responce,HttpStatus.BAD_REQUEST);		
	}

}
