package com.er.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.er.model.AccountDetails;
import com.er.model.AllPersonalDocuments;
import com.er.model.CustomerAddress;
import com.er.model.CustomerLoanApplication;
import com.er.model.DependentInformation;
import com.er.model.EREnquiry;
import com.er.model.GuarantorDetails;
import com.er.model.LocalAddress;
import com.er.model.PermanentAddress;
import com.er.repository.CustomerLoanApplicationRepo;
import com.er.repository.ERRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerLoanApplicationServiceImpl implements CustomerLoanApplicationServiceI {
	@Autowired
	ERRepository er;
	
	@Autowired
	CustomerLoanApplicationRepo clr;

	@Override
	public List<CustomerLoanApplication> getAllLoanData() {
		List<CustomerLoanApplication> list=clr.findAll();
		return list;
		
	}

	@Override
	public CustomerLoanApplication getLoanById(int customerID) {
		Optional<CustomerLoanApplication> op=clr.findById(customerID);
		if(op.isPresent())
		{
			CustomerLoanApplication cla =op.get();
			return cla;
		}else {
			throw new RuntimeException("Customer Id is invalid");
		}
		
	}
	
	
	
	@Override
	public CustomerLoanApplication addLoanData( String loanDataJson, MultipartFile addressProof,
			MultipartFile panCard, MultipartFile aadharCard, MultipartFile photo, MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips, MultipartFile incomeTax) throws IOException {
		
		
		ObjectMapper mapper = new ObjectMapper();
	   CustomerLoanApplication loanData = mapper.readValue(loanDataJson, CustomerLoanApplication.class);
		        AllPersonalDocuments docs = new AllPersonalDocuments();
		       
		        docs.setAddressProof(addressProof.getBytes());
		        docs.setPanCard(panCard.getBytes());
		        docs.setAadharCard(aadharCard.getBytes());
		        docs.setPhoto(photo.getBytes());
		        docs.setSignature(signature.getBytes());
		        docs.setBankCheque(bankCheque.getBytes());
		        docs.setSalarySlips(salarySlips.getBytes());
		        docs.setIncomeTax(incomeTax.getBytes());

		        loanData.setAllpersonalDocument(docs);

		        
		        int customerId = loanData.getCustomerID();
		        EREnquiry enquiry = er.findById(customerId).get();

		        loanData.setCustomerID(enquiry.getCustomerID());
		        loanData.setCustomerName(enquiry.getFirstName() + " " + enquiry.getLastName());
		        loanData.setAge(enquiry.getAge());
		        loanData.setCustomerEmail(enquiry.getEmail());
		        loanData.setCustomerMobileNumber(enquiry.getMobileNo());
		        loanData.setCibilscore(enquiry.getCibil());

		        return clr.save(loanData);

		    
	}

	@Override
	public CustomerLoanApplication updateLoanData(String loanData, MultipartFile addressProof, MultipartFile panCard,
			MultipartFile aadharCard, MultipartFile photo, MultipartFile signature, MultipartFile bankCheque,
			MultipartFile salarySlips, MultipartFile incomeTax) throws IOException {
		
		
		ObjectMapper mapper = new ObjectMapper();
		   CustomerLoanApplication updateData = mapper.readValue(loanData, CustomerLoanApplication.class);
		   
		   Optional<CustomerLoanApplication> optional = clr.findById(updateData.getCustomerID());
		    if (!optional.isPresent()) {
		        throw new RuntimeException("Customer ID not found for update");
		    }

		    CustomerLoanApplication existingData = optional.get();
			        AllPersonalDocuments docs = existingData.getAllpersonalDocument();
			        
			        if (docs == null) {
			            docs = new AllPersonalDocuments();
			        }
			       
			    
			        if (addressProof != null && !addressProof.isEmpty()) {
			            docs.setAddressProof(addressProof.getBytes());
			        }
			        if (panCard != null && !panCard.isEmpty()) {
			           docs.setPanCard(panCard.getBytes());
			        }
			        if (aadharCard != null && !aadharCard.isEmpty()) {
			            docs.setAadharCard(aadharCard.getBytes());
			        }
			        if (photo != null && !photo.isEmpty()) {
			           docs.setPhoto(photo.getBytes());
			        }
			        if (signature != null && !signature.isEmpty()) {
			            docs.setSignature(signature.getBytes());
			        }
			        if (bankCheque != null && !bankCheque.isEmpty()) {
			            docs.setBankCheque(bankCheque.getBytes());
			        }
			        if (salarySlips != null && !salarySlips.isEmpty()) {
			           docs.setSalarySlips(salarySlips.getBytes());
			        }
			        if (incomeTax != null && !incomeTax.isEmpty()) {
			           docs.setIncomeTax(incomeTax.getBytes());
			        }


			        existingData.setAllpersonalDocument(docs);

			        
			        int customerId = existingData.getCustomerID();
			        EREnquiry enquiry = er.findById(customerId).get();

			        existingData.setCustomerID(enquiry.getCustomerID());
			        existingData.setCustomerName(enquiry.getFirstName() + " " + enquiry.getLastName());
			        existingData.setAge(enquiry.getAge());
			        existingData.setCustomerEmail(enquiry.getEmail());
			        existingData.setCustomerMobileNumber(enquiry.getMobileNo());
			        existingData.setCibilscore(enquiry.getCibil());
			        
			        
			        
			        existingData.setDateOfBirth(updateData.getDateOfBirth());
			        existingData.setRequiredTenure(updateData.getRequiredTenure());
			        existingData.setCustomerGender(updateData.getCustomerGender());
			        existingData.setAdditionalMobileNumber(updateData.getAdditionalMobileNumber());
//			        existingData.setAmountPaidForHome(updateData.getAmountPaidForHome());
			        existingData.setAmountPaidForCar(updateData.getAmountPaidForCar());
			        existingData.setTotalLoanRequired(updateData.getTotalLoanRequired());
			        existingData.setLoanStatus(updateData.getLoanStatus());
			        
			        //Update Customer Address
			        CustomerAddress newAddress = updateData.getCustomerAddress();
			        CustomerAddress existingAddress = existingData.getCustomerAddress();

			        if (newAddress != null) {
			            if (existingAddress == null) {
			                // No address exists, just set the whole object
			                existingData.setCustomerAddress(newAddress);
			            } else {
			                // Preserve the address ID to avoid insert
			                newAddress.setCustomerAddressId(existingAddress.getCustomerAddressId());

			                // Update PermanentAddress
			                PermanentAddress newPermanent = newAddress.getPermanentAddress();
			                PermanentAddress existingPermanent = existingAddress.getPermanentAddress();
			                if (newPermanent != null) {
			                    if (existingPermanent == null) {
			                        existingAddress.setPermanentAddress(newPermanent);
			                    } else {
			                        newPermanent.setPermanentAddressId(existingPermanent.getPermanentAddressId());
			                        existingPermanent.setAreaname(newPermanent.getAreaname());
			                        existingPermanent.setCityname(newPermanent.getCityname());
			                        existingPermanent.setDistrict(newPermanent.getDistrict());
			                        existingPermanent.setState(newPermanent.getState());
			                        existingPermanent.setPincode(newPermanent.getPincode());
			                        existingPermanent.setHouseNumber(newPermanent.getHouseNumber());
			                        existingPermanent.setStreetName(newPermanent.getStreetName());
			                    }
			                }

			                // Update LocalAddress
			                LocalAddress newLocal = newAddress.getLocalAddress();
			                LocalAddress existingLocal = existingAddress.getLocalAddress();
			                if (newLocal != null) {
			                    if (existingLocal == null) {
			                        existingAddress.setLocalAddress(newLocal);
			                    } else {
			                        newLocal.setLocalAddressId(existingLocal.getLocalAddressId());
			                        existingLocal.setAreaname(newLocal.getAreaname());
			                        existingLocal.setCityname(newLocal.getCityname());
			                        existingLocal.setDistrict(newLocal.getDistrict());
			                        existingLocal.setState(newLocal.getState());
			                        existingLocal.setPincode(newLocal.getPincode());
			                        existingLocal.setHouseNumber(newLocal.getHouseNumber());
			                        existingLocal.setStreetName(newLocal.getStreetName());
			                    }
			                }
			            }
			        }



			        
			        //Update Dependent Information
			        DependentInformation newDepInfo = updateData.getFamilydependentInfo();
			        DependentInformation existingDepInfo = existingData.getFamilydependentInfo();

			        if (newDepInfo != null) {
			            if (existingDepInfo == null) {
			                // If no DependentInformation exists, just set the new one
			                existingData.setFamilydependentInfo(newDepInfo);
			            } else {
			                // Update existing entity
			                newDepInfo.setDependenntInfoId(existingDepInfo.getDependenntInfoId());

			                existingDepInfo.setNoOfFamilyMember(newDepInfo.getNoOfFamilyMember());
			                existingDepInfo.setNoOfChild(newDepInfo.getNoOfChild());
			                existingDepInfo.setMaritalStatus(newDepInfo.getMaritalStatus());
			                existingDepInfo.setDependentMember(newDepInfo.getDependentMember());
			                existingDepInfo.setFamilyIncome(newDepInfo.getFamilyIncome());
			            }
			        }

			        
			        
			     // Update Account Details
			        AccountDetails newAccount = updateData.getAccountdetails();
			        AccountDetails existingAccount = existingData.getAccountdetails();

			        if (newAccount != null) {
			            if (existingAccount == null) {
			                // If no AccountDetails exists, just set the new one
			                existingData.setAccountdetails(newAccount);
			            } else {
			                // Updating existing entity 
			                newAccount.setAccountId(existingAccount.getAccountId());

			                existingAccount.setAccountType(newAccount.getAccountType());
			                existingAccount.setAccountBalance(newAccount.getAccountBalance());
			                existingAccount.setAccountHolderName(newAccount.getAccountHolderName());
			                existingAccount.setAccountStatus(newAccount.getAccountStatus());
			                existingAccount.setAccountNumber(newAccount.getAccountNumber());
			            }
			        }

			        
			        
			     // Update Guarantor Details
			        GuarantorDetails newGuarantor = updateData.getGuarantordetails();
			        GuarantorDetails existingGuarantor = existingData.getGuarantordetails();

			        if (newGuarantor != null) {
			            if (existingGuarantor == null) {
			                // If no existing data, directly set new one
			                existingData.setGuarantordetails(newGuarantor);
			            } else {
			                // Keep the existing ID for update
			                newGuarantor.setGuarantorId(existingGuarantor.getGuarantorId());

			                // Update all fields
			                existingGuarantor.setGuarantorName(newGuarantor.getGuarantorName());
			                existingGuarantor.setGuarantorDateOfBirth(newGuarantor.getGuarantorDateOfBirth());
			                existingGuarantor.setGuarantorRelationshipWithCustomer(newGuarantor.getGuarantorRelationshipWithCustomer());
			                existingGuarantor.setGuarantorMobileNumber(newGuarantor.getGuarantorMobileNumber());
			                existingGuarantor.setGuarantorAdharCardNo(newGuarantor.getGuarantorAdharCardNo());
			                existingGuarantor.setGuarantorMortgageDetails(newGuarantor.getGuarantorMortgageDetails());
			                existingGuarantor.setGuarantorJobDetails(newGuarantor.getGuarantorJobDetails());
			                existingGuarantor.setGuarantorLocalAddress(newGuarantor.getGuarantorLocalAddress());
			                existingGuarantor.setGuarantorPermanentAddress(newGuarantor.getGuarantorPermanentAddress());
			            }
			        }


			        return clr.save(existingData);
		
	}


	@Override
	public List<CustomerLoanApplication> getAllSubmittedData() {
		List<CustomerLoanApplication> list1=clr.findAll();
		return list1;
	}

	@Override
	public List<CustomerLoanApplication> getVerifiedData(String loanStatus) {

	    if ("Verified".equalsIgnoreCase(loanStatus)) {
	        return clr.findByLoanStatusIgnoreCase(loanStatus);
	    }
	    return new ArrayList<>();
	}

//get All Loan application data
	@Override
	public List<CustomerLoanApplication> getAllApplication() {
		List<CustomerLoanApplication> custList=clr.findAll();
		return custList;
	}

	@Override
	public List<CustomerLoanApplication> getNotVerifiedData(String loanStatus) {
		if("NotVerified".equalsIgnoreCase(loanStatus))
		{
			return clr.findByLoanStatusIgnoreCase(loanStatus);
		}
		return new ArrayList<>();

	}
	



	@Override
	public CustomerLoanApplication getSingleCustomerData(int customerID) {
		Optional<CustomerLoanApplication> optional=clr.findById(customerID);
		if(optional.isPresent())
		{
			CustomerLoanApplication customer=optional.get();
			return customer;
		}
		
		else {
			
			 throw new RuntimeException(" Customer data is not available..");
		}
	}
	

}
