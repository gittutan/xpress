package com.wuyuncheng.xpress.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostSlugValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface PostSlugValidation {

    String message() default "文章别名效验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
