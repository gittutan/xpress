package com.wuyuncheng.xpress.model.enums.annotation;

import javax.validation.Payload;

public @interface ValidateString {

    String[] acceptedValues();

    String message() default "参数效验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}