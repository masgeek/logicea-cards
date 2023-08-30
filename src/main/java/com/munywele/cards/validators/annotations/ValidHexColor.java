package com.munywele.cards.validators.annotations;

import com.munywele.cards.validators.HexColorValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HexColorValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHexColor {
    String message() default "Invalid hex color code";

    boolean required() default true; // New attribute

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
