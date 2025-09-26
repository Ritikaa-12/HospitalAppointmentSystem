package com.it.entities;

import java.time.LocalDate;

import com.it.models.RecepModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Receptionist extends User{
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="joining_date" , nullable=false)
	private LocalDate joiningDate;
	
	@Column(name="leaving_date" , nullable=true)
	private LocalDate leavingDate;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	public Receptionist(RecepModel model,String pwd) {
		this.name=model.getName();
		this.joiningDate=model.getJoiningDate();
		this.setMobile(model.getMobile());
		this.setEmail(model.getEmail());
		this.setPassword(pwd);
		this.setActiveStatus(true);
		this.setRole("ROLE_RECEP");
	}
	
	
}
