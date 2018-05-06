package com.contaazul.bankslips.exception.handler;

import com.contaazul.bankslips.response.error.BankslipErrorResponse;
import com.contaazul.bankslips.exception.BankskipNotFoundException;
import com.contaazul.bankslips.exception.InvalidBankslipException;
import com.contaazul.bankslips.exception.InvalidUUIDException;
import com.contaazul.bankslips.model.Messages;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Paulo
 */
@ControllerAdvice
public class BankslipExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        BankslipErrorResponse error = new BankslipErrorResponse()
                .withStatus(BAD_REQUEST.value())
                .withError(Messages.BANKSLIP_NOT_PROVIDED.getMessage());

        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBankslipException.class)
    public ResponseEntity<?> handleInvalidBankslipException(InvalidBankslipException ex) {
        BankslipErrorResponse error = new BankslipErrorResponse()
                .withStatus(UNPROCESSABLE_ENTITY.value())
                .withError(ex.getMessage())
                .withMessage("A field of the provided bankslip was null or with invalid values");

        return new ResponseEntity<>(error, UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidUUIDException.class)
    public ResponseEntity<?> handleInvalidUUIDException(InvalidUUIDException ex) {
        BankslipErrorResponse error = new BankslipErrorResponse()
                .withStatus(BAD_REQUEST.value())
                .withError(ex.getMessage());

        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(BankskipNotFoundException.class)
    public ResponseEntity<?> handleBankskipNotFoundException(BankskipNotFoundException ex) {
        BankslipErrorResponse error = new BankslipErrorResponse()
                .withStatus(NOT_FOUND.value())
                .withError(ex.getMessage());

        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}
