package com.it.models;

import org.hibernate.validator.constraints.Length;

import com.it.validators.ValidSpecs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorModel {
	@NotNull(message="Doctor Name can't be NULL")
	@NotEmpty(message="Doctor Name can't be Empty")
   private String name;
	
	@NotNull(message="Doctor Mobile can't be NULL")
	@NotEmpty(message="Doctor Mobile can't be Empty")
	@Length(min=0,max=10,message="wrong mobile number")
   private String mobile;
	
	@NotNull(message="Doctor Specialization can't be NULL")
	@NotEmpty(message="Doctor Specialization can't be Empty")
	@ValidSpecs
   private String specs;
	
	@NotNull(message="Doctor Timing can't be NULL")
	@NotEmpty(message="Doctor Timing can't be Empty")
   private String timing;
	
	@NotNull(message="Doctor Fees can't be NULL")
	@Min(value=200,message="Doctor fees incorrect")
   private Float fees;
}
