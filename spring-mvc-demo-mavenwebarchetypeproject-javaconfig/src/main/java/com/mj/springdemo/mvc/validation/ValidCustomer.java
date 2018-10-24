package com.mj.springdemo.mvc.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//Class-level annotation
@Constraint(validatedBy=ValidCustomerConstraintValidator.class)
@Retention(RUNTIME)
@Target({ TYPE })
public @interface ValidCustomer {
	public String message() default "invalid customer";
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
