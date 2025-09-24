package com.er.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@CrossOrigin("*")
@Entity
public class DependentInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dependenntInfoId;
	private int noOfFamilyMember;
	private int noOfChild;
	private String maritalStatus;
	private String dependentMember;
	private double familyIncome;
	public int getDependenntInfoId() {
		return dependenntInfoId;
	}
	public void setDependenntInfoId(int dependenntInfoId) {
		this.dependenntInfoId = dependenntInfoId;
	}
	public int getNoOfFamilyMember() {
		return noOfFamilyMember;
	}
	public void setNoOfFamilyMember(int noOfFamilyMember) {
		this.noOfFamilyMember = noOfFamilyMember;
	}
	public int getNoOfChild() {
		return noOfChild;
	}
	public void setNoOfChild(int noOfChild) {
		this.noOfChild = noOfChild;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getDependentMember() {
		return dependentMember;
	}
	public void setDependentMember(String dependentMember) {
		this.dependentMember = dependentMember;
	}
	public double getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(double familyIncome) {
		this.familyIncome = familyIncome;
	}
	
	
	

}
