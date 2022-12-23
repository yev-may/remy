package com.yevay.remy.model.dto.form.validator.impl;

import com.yevay.remy.model.dto.form.UserRegistrationForm;
import com.yevay.remy.model.dto.form.validator.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRegistrationForm> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegistrationForm value, ConstraintValidatorContext context) {
        return Objects.equals(value.getPassword(), value.getRepeatPassword());
    }
}
