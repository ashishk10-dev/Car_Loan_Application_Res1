package com.as.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.as.dto.EmployeeDetailsDTO;

import com.as.model.EmployeeDetails;
import com.as.service.AdminServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
public class AdminServiceController {
	
	@Autowired
	AdminServiceI asi;
	
	
	@PostMapping(value = "/addEmpData", consumes="multipart/form-data")
	public ResponseEntity<EmployeeDetails> addData(@RequestPart("empData")String empDataJson,
			                                        @RequestPart("empImage")MultipartFile empImage,
			                                        @RequestPart("empPancard")MultipartFile empPancard) throws IOException
	{
		EmployeeDetails emp= asi.addData(empDataJson,empImage,empPancard);
		return new ResponseEntity<EmployeeDetails>(emp,HttpStatus.CREATED);
	}
	

	@GetMapping("/userLogin/{username}/{password}")
	public ResponseEntity<EmployeeDetails> userLogin(@PathVariable String username, @PathVariable String password)
	{
		System.out.println(username);
		System.out.println(password);
		EmployeeDetails user = asi.loginCheck(username,password);
		return new ResponseEntity<EmployeeDetails> (user, HttpStatus.OK);
	}

// Get all employee records
	@GetMapping("/viewEmployeesAPI")
    public List<EmployeeDetailsDTO> getAllEmployeesWithImages() {
        return asi.getAllEmployeeDTOs();
    }
	
// Delete employee by empId Api 
	@DeleteMapping("/deleteEmployee/{empId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int empId) {
	    asi.deleteEmployeeById(empId);
	    return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
	}


// Update employee by empId with images API

	@PutMapping(value = "/updateEmpData/{id}", consumes = "multipart/form-data")
	public ResponseEntity<EmployeeDetails> updateData(@PathVariable int id,
	                                                  @RequestPart("empData") String empDataJson,
	                                                  @RequestPart(value = "empImage", required = false) MultipartFile empImage,
	                                                  @RequestPart(value = "empPancard", required = false) MultipartFile empPancard) throws IOException {
	   

	        EmployeeDetails updatedEmp = asi.updateData(id, empDataJson, empImage, empPancard);
	        return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
	    } 

//getallEmpoyeeData
	@GetMapping("/getAllEmpData")
	public ResponseEntity<List<EmployeeDetails>>  getAllEmployeeData()
	{
		List<EmployeeDetails> Elist= asi.getAll();
		return new ResponseEntity<List<EmployeeDetails>>(Elist,HttpStatus.OK);
	}
	
//getByEmployeeID
	@GetMapping("/getByEmpId/{empId}")
	public ResponseEntity<EmployeeDetails> getSingleCustomer(@PathVariable int empId)
	{
		EmployeeDetails employee=asi.getEmpSingleId(empId);
		return new ResponseEntity<EmployeeDetails >(employee,HttpStatus.OK);
	}
	
//deletByEmployeeId	
	@DeleteMapping("/deleteById/{empId}")
	public ResponseEntity<String> deleteByCustomerId(@PathVariable int empId)
    {
     	asi.deleteEmployeeId(empId);
	    return new ResponseEntity<String>(" Employee data deleted ",HttpStatus.OK);
    }
	


// Update employee by empId API

	@PutMapping("/updateEmployee/{empId}")
	public ResponseEntity<EmployeeDetails> updateEmployeeWithImages(
	        @PathVariable int empId,
	        @RequestPart("empDataJson") String empDataJson,
	        @RequestPart(value = "empImage", required = false) MultipartFile empImage,
	        @RequestPart(value = "empPancard", required = false) MultipartFile empPancard) throws IOException {

	    EmployeeDetails updated = asi.updateEmployeeWithImages(empDataJson, empImage, empPancard, empId);
	    return ResponseEntity.ok(updated);
	}
}
