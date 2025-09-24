package com.er.model;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@CrossOrigin("*")
@Entity
public class Cibil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cibilId;
	private int cibilScore;
	private Date cibilScoreDateTime;
	private String status;
	private String cibilRemark;
	
	
	public int getCibilId() {
		return cibilId;
	}
	public void setCibilId(int cibilId) {
		this.cibilId = cibilId;
	}
	public int getCibilScore() {
		return cibilScore;
	}
	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}
	public Date getCibilScoreDateTime() {
		return cibilScoreDateTime;
	}
	public void setCibilScoreDateTime(Date cibilScoreDateTime) {
		this.cibilScoreDateTime = cibilScoreDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCibilRemark() {
		return cibilRemark;
	}
	public void setCibilRemark(String cibilRemark) {
		this.cibilRemark = cibilRemark;
	}
	
	

}
