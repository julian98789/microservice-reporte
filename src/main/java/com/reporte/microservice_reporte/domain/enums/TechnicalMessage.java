package com.reporte.microservice_reporte.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TechnicalMessage {

    INTERNAL_ERROR("500","Something went wrong, please try again", "");


    private final String code;
    private final String message;
    private final String param;
}