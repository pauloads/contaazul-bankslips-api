package com.contaazul.bankslips.model;

/**
 *
 * @author Paulo
 */
public enum Messages {

    BANKSLIP_CREATED("Bankslip created"),
    BANKSLIP_NOT_PROVIDED("Bankslip not provided in the request body"),
    INVALID_BANKSLIP_PROVIDED("Invalid bankslip provided."),
    INVALID_ID_PROVIDED("Invalid id provided - it must be a valid UUID"),
    BANKSLIP_NOT_FOUND("Bankslip not found with the specified id"),
    BANKSLIP_PAID("Bankslip paid"),
    BANKSLIP_CANCELED("Bankslip canceled");

    private final String message;

    private Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
