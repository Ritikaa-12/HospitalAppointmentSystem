package com.it.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PWDModel {

	@NotNull(message = "Old Password Can't be Null")
	@NotEmpty(message="Old Password can't be Empty")
	private String oldpwd;
	
	@NotNull(message = "New Password Can't be Null")
	@NotEmpty(message="New Password can't be Empty")
	private String newpwd;
}
