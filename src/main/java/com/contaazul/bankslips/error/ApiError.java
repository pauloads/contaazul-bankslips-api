package com.contaazul.bankslips.error;

import java.util.List;

/**
 *
 * @author Paulo
 */
public class ApiError {

    private Integer status;

    private String error;

    private String message;

    public ApiError withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ApiError withError(String error) {
        this.error = error;
        return this;
    }

    public ApiError withMessage(String message) {
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
