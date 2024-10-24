package com.example.modulith.customer.validation.phone;

import com.example.modulith.dto.customer.CustomerRequest;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CorrectPhoneValidator implements ConstraintValidator<CorrectPhoneNumber, CustomerRequest> {

    @Override
    public boolean isValid(CustomerRequest request, ConstraintValidatorContext constraintValidatorContext) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phone;

        if (request.phoneNumber() == null) return true;

        try {
            phone = phoneNumberUtil.parse(request.phoneNumber(), Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
            return phoneNumberUtil.isValidNumber(phone);
        } catch (NumberParseException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
