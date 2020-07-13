package com.example.demo.services.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.entities.SecureUsers;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, SecureUsers> {

	@Override
	public void initialize(PasswordMatching pm) {
		pm.message();
	}
	
	@Override
	public boolean isValid(SecureUsers instance, ConstraintValidatorContext context) {	
		return instance.getPassword().equals(instance.getMatchingPassword());
	}

}
