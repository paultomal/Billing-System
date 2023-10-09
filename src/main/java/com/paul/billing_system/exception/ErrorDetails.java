package com.paul.billing_system.exception;

import lombok.Data;


@Data
public class ErrorDetails {
    private String details;

    public ErrorDetails(String details) {
        super();
        this.details = details;
    }

}
