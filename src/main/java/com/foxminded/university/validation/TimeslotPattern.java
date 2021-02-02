package com.foxminded.university.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TimeslotPatternConstraintValidator.class)
public @interface TimeslotPattern {
    String message() default "Wrong time format for pattern \"hh:mm - hh:mm\"";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
