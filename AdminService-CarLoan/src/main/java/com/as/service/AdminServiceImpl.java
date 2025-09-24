package com.as.service;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Base64;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.as.dto.EmployeeDetailsDTO;
import com.as.enums.UserType;
import com.as.model.EmployeeDetails;
import com.as.repository.AdminServiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import randomuserpass.CredentialGenerator;


@Service
public class AdminServiceImpl implements AdminServiceI {
	
	@Autowired 
	AdminServiceRepository asr;
	
	   @Autowired
	 	private JavaMailSender mailSender;
	     
	     @Value("${spring.mail.username}")
	 	private String SEND_MAIL;

	@Override
	public EmployeeDetails addData(String empDataJson, MultipartFile empImage, MultipartFile empPancard) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		  EmployeeDetails loanData = mapper.readValue(empDataJson, EmployeeDetails.class);
		  
		  loanData.setEmpImage(empImage.getBytes());
		  loanData.setEmpPancard(empPancard.getBytes());
		  if (loanData.getUsername() == null || loanData.getUsername().isEmpty()) {
			  LocalDate dob = LocalDate.parse(loanData.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			    String generatedUsername = CredentialGenerator.generateUsername(
			        loanData.getEmpFirstName(),
			        loanData.getEmpLastName(),
			        loanData.getDateOfBirth()
			    );
			    loanData.setUsername(generatedUsername);
			}

			if (loanData.getPassword() == null || loanData.getPassword().isEmpty()) {
				LocalDate dob = LocalDate.parse(loanData.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			    String generatedPassword = CredentialGenerator.generatePassword(
			    		loanData.getEmpFirstName(),
			    		loanData.getDateOfBirth()
			    );
			    loanData.setPassword(generatedPassword);
			}
			EmployeeDetails saved= asr.save(loanData);
			
			SimpleMailMessage simple= new SimpleMailMessage();
			simple.setTo(loanData.getEmpEmail());
			simple.setFrom(SEND_MAIL);
			simple.setSubject("Your Credentials for Logging In");
			String message = "Dear " + loanData.getEmpFirstName() + " " + loanData.getEmpLastName() + ",\n\n"
			        + "Welcome to the company!\n\n"
			        + "Your login credentials have been generated as follows:\n"
			        + "Username: " + loanData.getUsername() + "\n"
			        + "Password: " + loanData.getPassword() + "\n\n"
			        + "Please use these credentials to log in to the employee portal.\n"
			        + "It is recommended that you change your password after your first login.\n\n"
			        + "Regards,\n"
			        + "HR Team";	    

			simple.setText(message);
			
			mailSender.send(simple);
			return saved;	
	}


	@Override
	public EmployeeDetails updateData(int id, String empDataJson, MultipartFile empImage, MultipartFile empPancard)
			throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
        EmployeeDetails empDetails = mapper.readValue(empDataJson, EmployeeDetails.class);
Optional<EmployeeDetails> optional =asr .findById(id);
if (!optional.isPresent()) {
    throw new RuntimeException("Employee with ID " + id + " not found");
}

EmployeeDetails existing = optional.get();


existing.setEmpFirstName(empDetails.getEmpFirstName());
existing.setEmpLastName(empDetails.getEmpLastName());
existing.setEmpEmail(empDetails.getEmpEmail());
existing.setEmpSalary(empDetails.getEmpSalary());
existing.setEmpAge(empDetails.getEmpAge());
existing.setUserType(empDetails.getUserType());
existing.setDateOfBirth(empDetails.getDateOfBirth());

if (empImage != null && !empImage.isEmpty()) {
    existing.setEmpImage(empImage.getBytes());
}

if (empPancard != null && !empPancard.isEmpty()) {
    existing.setEmpPancard(empPancard.getBytes());
}

return asr.save(existing);
}


	@Override
	public EmployeeDetails loginCheck(String username, String password) {
		return asr.findByUsernameAndPassword(username, password);
	}


	//getAll Employees
		 @Override
		    public List<EmployeeDetailsDTO> getAllEmployeeDTOs() {
		        List<EmployeeDetails> employees = asr.findAll();
		        List<EmployeeDetailsDTO> dtoList = new ArrayList();

		        for (EmployeeDetails emp : employees) {
		            EmployeeDetailsDTO dto = new EmployeeDetailsDTO();
		            dto.setEmpId(emp.getEmpId());
		            dto.setEmpFirstName(emp.getEmpFirstName());
		            dto.setEmpLastName(emp.getEmpLastName());
		            dto.setEmpEmail(emp.getEmpEmail());
		            dto.setEmpSalary(emp.getEmpSalary());
		            dto.setEmpAge(emp.getEmpAge());
		            dto.setUsername(emp.getUsername());
		            dto.setPassword(emp.getPassword());
		            dto.setUserType(emp.getUserType());
		            dto.setDateOfBirth(emp.getDateOfBirth());

		            if (emp.getEmpImage() != null) {
		                dto.setEmpImage(Base64.getEncoder().encodeToString(emp.getEmpImage()));
		            }

		            if (emp.getEmpPancard() != null) {
		                dto.setEmpPancard(Base64.getEncoder().encodeToString(emp.getEmpPancard()));
		            }

		            dtoList.add(dto);
		        }

		        return dtoList;
		    }

	

//deleteEmployeeBy Id
	@Override
	public void deleteEmployeeById(int empId) {
	    asr.deleteById(empId);
	}
	
//update/edit employee by ID with images

	public EmployeeDetails updateEmployeeWithImages(String empDataJson, MultipartFile empImage, MultipartFile empPancard, int empId) throws IOException {
	    EmployeeDetails existing = asr.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found"));
	    ObjectMapper mapper = new ObjectMapper();
	    EmployeeDetails updatedEmp = mapper.readValue(empDataJson, EmployeeDetails.class);

	    existing.setEmpFirstName(updatedEmp.getEmpFirstName());
	    existing.setEmpLastName(updatedEmp.getEmpLastName());
	    existing.setEmpEmail(updatedEmp.getEmpEmail());
	    existing.setEmpAge(updatedEmp.getEmpAge());
	    existing.setEmpSalary(updatedEmp.getEmpSalary());
	    existing.setUserType(updatedEmp.getUserType());
	    existing.setUsername(updatedEmp.getUsername());
	    existing.setPassword(updatedEmp.getPassword());
	    existing.setDateOfBirth(updatedEmp.getDateOfBirth());

	    if (empImage != null && !empImage.isEmpty()) {
	        existing.setEmpImage(empImage.getBytes());
	    }
	    if (empPancard != null && !empPancard.isEmpty()) {
	        existing.setEmpPancard(empPancard.getBytes());
	    }

	    return asr.save(existing);
	}


	@Override
	public List<EmployeeDetails> getAll() {
		List<EmployeeDetails> Elist=asr.findAll();
		return Elist;
		
	}

	@Override
	public EmployeeDetails getEmpSingleId(int empId) {
		Optional<EmployeeDetails> op=asr.findById(empId);
		if(op.isPresent())
		{
			EmployeeDetails EmpD =op.get();
			return EmpD;
		}else {
			throw new RuntimeException("Employee Id is invalid");
		}
		
		
	}

	@Override
	public void deleteEmployeeId(int empId) {
		asr.deleteById(empId);
	}


	
}


