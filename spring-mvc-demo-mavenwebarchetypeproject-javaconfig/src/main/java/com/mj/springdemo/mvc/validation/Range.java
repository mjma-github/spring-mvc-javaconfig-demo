package com.mj.springdemo.mvc.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=RangeConstraintValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface Range {
	public int min() default 0;
	public int max() default Integer.MAX_VALUE;
	public String message() default "value must between 0 and " + Integer.MAX_VALUE;
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
