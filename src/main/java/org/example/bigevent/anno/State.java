package org.example.bigevent.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.bigevent.validation.StateValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented//元注解
@Constraint(validatedBy = { StateValidation.class })//指定提供校验规则的类
@Target({ FIELD })//指定该注解可以作用的位置
@Retention(RUNTIME)//指定该注解的保留时间
public @interface State {
    String message() default "{发布的参数只能是草稿或已发布状态}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
