package com.api.gamingplatform.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriceTypeEnumValidator implements ConstraintValidator<ValidPriceTypeEnum, String> {

    private List<String> values;

    @Override
    public void initialize(ValidPriceTypeEnum constraintAnnotation) {
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        return values.contains(s.toString());
    }
}
