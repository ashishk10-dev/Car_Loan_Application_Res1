package com.er.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@CrossOrigin("*")
@Entity
public class AllPersonalDocuments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int documentId;
	
	@Lob
	@Column(length=999999999)
	private byte[] addressProof;
	
	@Lob
	@Column(length=999999999)
	private byte[] panCard;
	
	@Lob
	@Column(length=999999999)
	private byte[] incomeTax;
	
	@Lob
	@Column(length=999999999)
	private byte[] aadharCard;
	
	@Lob
	@Column(length=999999999)
	private byte[] photo;
	
	@Lob
	@Column(length=999999999)
	private byte[] signature;
	
	@Lob
	@Column(length=999999999)
	private  byte[] bankCheque;
	
	@Lob
	@Column(length=999999999)
	private byte[] salarySlips;

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public byte[] getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(byte[] addressProof) {
		this.addressProof = addressProof;
	}

	public byte[] getPanCard() {
		return panCard;
	}

	public void setPanCard(byte[] panCard) {
		this.panCard = panCard;
	}

	public byte[] getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(byte[] incomeTax) {
		this.incomeTax = incomeTax;
	}

	public byte[] getAadharCard() {
		return aadharCard;
	}

	public void setAadharCard(byte[] aadharCard) {
		this.aadharCard = aadharCard;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public byte[] getBankCheque() {
		return bankCheque;
	}

	public void setBankCheque(byte[] bankCheque) {
		this.bankCheque = bankCheque;
	}

	public byte[] getSalarySlips() {
		return salarySlips;
	}

	public void setSalarySlips(byte[] salarySlips) {
		this.salarySlips = salarySlips;
	}

	
	
}
	
	