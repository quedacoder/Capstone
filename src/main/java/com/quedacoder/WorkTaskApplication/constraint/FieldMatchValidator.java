package com.quedacoder.WorkTaskApplication.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	
	private String firstFieldName;
	private String secondFieldName;
	
	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
	}

	@Override
	public boolean isValid(Object value, final ConstraintValidatorContext context) {
		try {
            final Object firstObj = BeanUtils.getPropertyDescriptor((Class<?>) value, firstFieldName);
            final Object secondObj = BeanUtils.getPropertyDescriptor((Class<?>) value, secondFieldName);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {}
        return true;
	}

}
