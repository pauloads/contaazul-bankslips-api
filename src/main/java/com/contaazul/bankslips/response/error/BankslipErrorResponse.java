package com.contaazul.bankslips.response.error;

import java.util.List;

/**
 *
 * @author Paulo
 */
public class BankslipErrorResponse {

    private Integer status;

    private String error;

    private String message;

    public BankslipErrorResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public BankslipErrorResponse withError(String error) {
        this.error = error;
        return this;
    }

    public BankslipErrorResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
