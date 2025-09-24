package com.er.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@CrossOrigin("*")
@Entity
public class CustomerAddress {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerAddressId;
	
     @OneToOne(cascade=CascadeType.ALL)
	private PermanentAddress permanentAddress;

     @OneToOne(cascade=CascadeType.ALL)
	private LocalAddress localAddress;

	public int getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(int customerAddressId) {
		this.customerAddressId = customerAddressId;
	}

	public PermanentAddress getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(PermanentAddress permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public LocalAddress getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(LocalAddress localAddress) {
		this.localAddress = localAddress;
	}
}
