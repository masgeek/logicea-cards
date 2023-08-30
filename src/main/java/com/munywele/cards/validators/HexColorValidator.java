package com.munywele.cards.validators;

import com.munywele.cards.utils.ColorValidator;
import com.munywele.cards.validators.annotations.ValidHexColor;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;


public class HexColorValidator implements ConstraintValidator<ValidHexColor, String> {
    private boolean required;

    @Override
    public void initialize(ValidHexColor constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return !required;
        }
        return ColorValidator.isValidHexColor(value);
    }
}
