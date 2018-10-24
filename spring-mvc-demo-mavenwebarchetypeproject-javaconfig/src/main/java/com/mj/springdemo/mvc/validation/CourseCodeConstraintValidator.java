package com.mj.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//ConstraintValidator<(Annotation type), (annotated element type)>
public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
	private String[] coursePrefixes;
	
	//access the annotation type's attribute values here
	@Override
	public void initialize(CourseCode constraintAnnotation) {
		coursePrefixes = constraintAnnotation.value();
	}

	//value is the value of the annotated element
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		
		for(String coursePrefix : coursePrefixes) {
			if(value.toLowerCase().startsWith(coursePrefix.toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
}
