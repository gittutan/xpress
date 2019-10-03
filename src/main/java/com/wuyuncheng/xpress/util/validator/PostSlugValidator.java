package com.wuyuncheng.xpress.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostSlugValidator implements ConstraintValidator<PostSlugValidation, String> {

    @Override
    public void initialize(PostSlugValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (null == value) {
            return true;
        }
        char firstChar = value.toCharArray()[0];
        if (firstChar >= '0' && firstChar <= '9') {
            return false;
        }
        return true;
    }

}
