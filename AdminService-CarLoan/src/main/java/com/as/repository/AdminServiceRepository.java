package com.as.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.model.EmployeeDetails;

@Repository
public interface AdminServiceRepository extends JpaRepository<EmployeeDetails, Integer>{

	public EmployeeDetails findByUsernameAndPassword(String username, String password);


}
