package com.mj.springdemo.mvc.validation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mj.springdemo.mvc.model.Customer;

public class ValidCustomerConstraintValidator implements ConstraintValidator<ValidCustomer, Customer> {

	@Override
	public void initialize(ValidCustomer constraintAnnotation) {
		// do nothing, annotation attribute values will not be used for validation for
		// this example
	}

	@Override
	public boolean isValid(Customer customer, ConstraintValidatorContext context) {
		/* age range : allowed free passes
		 * 1-10:10
		 * 11-20:5
		 * 30-59:2
		 * 60 up:10
		 */
		
		if(customer.getBirthDate() == null) {
			return true;
		}
		
		long numberOfYears = ChronoUnit.YEARS.between(customer.getBirthDate(), LocalDate.now());
		int freePasses = customer.getFreePasses();
		
		if(numberOfYears >= 1 && numberOfYears <= 10 && freePasses <= 10) {
			return true;
		}
		if(numberOfYears >= 11 && numberOfYears <= 20 && freePasses <= 5) {
			return true;
		}
		if(numberOfYears >= 30 && numberOfYears <= 59 && freePasses <= 2) {
			return true;
		}
		if(numberOfYears >= 60 && freePasses <= 10) {
			return true;
		}
		
		return false;
	}

}
