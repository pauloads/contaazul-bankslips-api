package com.contaazul.bankslips.validator;

import com.contaazul.bankslips.exception.InvalidBankslipException;
import com.contaazul.bankslips.model.BankSlip;
import static com.contaazul.bankslips.model.Messages.INVALID_BANKSLIP_PROVIDED;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author Paulo
 */
public class BankslipValidator {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void validate(BankSlip bankSlip) {

        Set<ConstraintViolation<BankSlip>> violations = validator.validate(bankSlip);

        if (!violations.isEmpty()) {
            throw new InvalidBankslipException(INVALID_BANKSLIP_PROVIDED.getMessage());
        }
    }
}
