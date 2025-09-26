package com.it.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppointmentRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="request_id")
	private Integer requestId;
	
	@ManyToOne
	@JoinColumn(name="patient",nullable = false)
	private Patient patient;
	
	@Column(name="appointment_date", nullable =false)
	private LocalDate appointmentDate;
	
	@Column(name="request_date", nullable =false)
	private LocalDate requestDate;
	
	@ManyToOne
	@JoinColumn(name="doctor",nullable=false)
	private Doctor doctor;
	
	@Column(name="active_status",nullable=false)
	private Boolean activeStatus;

	@Column(name="status",nullable=false)
	private String status;
	
	@Column(name="cancel_desc",nullable=true)
	private String cancelDescription;
}
