package com.cvc.test.transfer.entrypoint.exception;

import lombok.Data;

@Data
public class ExceptionDTO {
    private final Integer statusCode;
    private final String message;
    private String detail;
}
