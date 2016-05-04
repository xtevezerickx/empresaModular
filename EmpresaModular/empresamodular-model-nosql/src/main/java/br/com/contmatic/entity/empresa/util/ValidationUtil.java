package br.com.contmatic.entity.empresa.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class ValidationUtil {
    /**
     * Instantiates a new validation util.
     */
    private ValidationUtil() {

    }

    /**
     * Has errors.
     *
     * @param obj the obj
     * @param message the message
     * @return true, if successful
     */
    public static boolean hasErrors(Object obj, String message) {
        if (message != null && obj != null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> errors = validator.validate(obj);
            for(ConstraintViolation<Object> constraintViolation : errors) {
                if (message.equals(constraintViolation.getMessage())) {
                    return true;
                }
            }
        }
        return false;

    }
}
