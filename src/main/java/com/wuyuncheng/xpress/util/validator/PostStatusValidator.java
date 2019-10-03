package com.wuyuncheng.xpress.util.validator;

import com.wuyuncheng.xpress.model.enums.PostStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class PostStatusValidator implements ConstraintValidator<PostStatusValidation, String> {

    private List<String> valueList;

    @Override
    public void initialize(PostStatusValidation constraintAnnotation) {
        valueList = new ArrayList<>();
        valueList.add(PostStatus.PUBLISH.getValue());
        valueList.add(PostStatus.DRAFT.getValue());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!valueList.contains(value)) {
            return false;
        }
        return true;
    }

}