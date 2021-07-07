package com.hiberus.employee.directory.vo.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * NotBlank.
 * 
 * @author acachiguango on 29/10/2019
 * @version 1.0
 * @since 1.0.0
 */
public class NotBlankValidator implements ConstraintValidator<NotBlankConstraint, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(NotBlankConstraint value) {
        /**
         * NotBlankValidator.
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        if (null != contactField && contactField.trim().length() > 0) {
            return true;
        }
        return false;
    }
}
