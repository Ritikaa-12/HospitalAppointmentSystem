package com.it.validators;

import java.util.Arrays;
import java.util.List;

import com.it.utils.SystemConstants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SpecsValidator  implements ConstraintValidator<ValidSpecs, String> {
  
	@Override
	public boolean isValid(String value,ConstraintValidatorContext  context) {
		List<String> list=Arrays.asList(SystemConstants.SPECIALIZATION);
		return list.contains(value);
	}
}
