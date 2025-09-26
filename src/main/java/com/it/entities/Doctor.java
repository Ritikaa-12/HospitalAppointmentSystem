package com.it.entities;

import com.it.models.DoctorModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Doctor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="doc_id")
    private Integer docId;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="mobile",nullable=false,unique=true)
	private String mobile;
	
	@Column(name="specialization",nullable=false)
	private String specialization;
	
	@Column(name="timing",nullable=false)
	private String timing;
	
	@Column(name="fees",nullable=false)
	private Float consultingFees;
	
	public Doctor(DoctorModel model) {
		this.name = model.getName();
		this.mobile = model.getMobile();
		this.specialization = model.getSpecs();
		this.timing = model.getTiming();
		this.consultingFees = model.getFees();
	}
}
