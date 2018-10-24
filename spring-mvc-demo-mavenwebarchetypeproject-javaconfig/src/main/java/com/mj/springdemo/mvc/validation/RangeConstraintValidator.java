package com.mj.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeConstraintValidator implements ConstraintValidator<Range, Integer> {
	private int min;
	private int max;

	@Override
	public void initialize(Range constraintAnnotation) {
		min = constraintAnnotation.min();
		max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		
		if (value >= min && value <= max) {
			return true;
		}

		return false;
	}
}
