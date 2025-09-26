package com.it.entities;

import java.time.LocalDate;

import com.it.models.AppointmentModel.PatientInfo;

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
public class Patient extends User
{
	

	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "gender",nullable = false)
	private String gender;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "dob",nullable = true)
	private LocalDate dob;
	
	public Patient(PatientInfo pinfo, String pwd) 
	{
		this.name = pinfo.getName();
		this.gender = pinfo.getGender();
		this.dob = pinfo.getDob();
		this.setMobile(pinfo.getMobile());
		this.setEmail(pinfo.getEmail());
		this.setPassword(pwd);
		this.setActiveStatus(true);
		this.setRole("ROLE_PATIENT");
	}
}
