package com.er.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@CrossOrigin("*")
@Entity
public class PermanentAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int permanentAddressId;
	private String areaname;
	private String cityname;
	private String district;
	private String state;
	private long pincode;
	private int houseNumber;
	private String streetName;
	public int getPermanentAddressId() {
		return permanentAddressId;
	}
	public void setPermanentAddressId(int permanentAddressId) {
		this.permanentAddressId = permanentAddressId;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	

}
