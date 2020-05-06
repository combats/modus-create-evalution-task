package com.modus.create.gateway.exception;

import lombok.Data;

@Data
public class ExceptionDto {

    private String message;

    public ExceptionDto(String format, Object... args) {
        this.message = String.format(format, args);
    }
}