package org.example.bigevent.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.bigevent.anno.State;

//需要传入生效的注解和校验的类型
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.equals("草稿")|| value.equals("已发布")) {
            return true;
        }
        return false;

    }
}
