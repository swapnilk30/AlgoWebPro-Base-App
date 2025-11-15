package com.algowebpro.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
public @interface PhoneNumber {
	String message() default "Invalid phone number format";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
