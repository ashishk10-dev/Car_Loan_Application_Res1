package com.as.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.as.dto.EmployeeDetailsDTO;
import com.as.model.EmployeeDetails;

public interface AdminServiceI {

	public EmployeeDetails addData(String empDataJson, MultipartFile empImage, MultipartFile empPancard) throws IOException;


	public EmployeeDetails loginCheck(String username, String password);

	public void deleteEmployeeById(int empId);


	public EmployeeDetails updateEmployeeWithImages(String empDataJson, MultipartFile empImage, MultipartFile empPancard, int empId) throws IOException;
	
	public List<EmployeeDetailsDTO> getAllEmployeeDTOs(); // for base64 encoded image

	


	public EmployeeDetails updateData(int id, String empDataJson, MultipartFile empImage,
			MultipartFile empPancard) throws IOException;

	public List<EmployeeDetails> getAll();

	public EmployeeDetails getEmpSingleId(int empId);

	public void deleteEmployeeId(int empId);




}
