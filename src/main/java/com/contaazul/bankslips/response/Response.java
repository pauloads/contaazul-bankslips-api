package com.contaazul.bankslips.response;

/**
 *
 * @author Paulo
 */
public class Response {

    private String message;

    public String getMessage() {
        return message;
    }

    public Response withMessage(String message) {
        this.message = message;
        return this;
    }
}
