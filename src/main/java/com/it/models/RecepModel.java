package com.it.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecepModel {
	@NotNull(message="Name can't be NULL")
	@NotEmpty(message="Name can't be empty")
	private String name;
	
	@NotNull(message="joiningDate can't be NULL")
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate joiningDate;
	
	@NotNull(message="Mobile can't be NULL")
	@NotEmpty(message="Mobile can't be empty")
	private String mobile;
	
	@NotNull(message = "Email Can't Be Null")
	@NotEmpty(message = "Email Can't Be Empty")
	private String email;

}
