package com.it.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentModel 
{
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private Integer doctorid;
	
	private PatientInfo pinfo;
	
	private Integer pid;
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class PatientInfo
	{
		private String name;
		private String mobile;
		private String gender;
		private String email;
		@JsonFormat(pattern = "dd/MM/yyyy")
		private LocalDate dob;
	}
}

