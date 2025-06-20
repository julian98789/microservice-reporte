package com.reporte.microservice_reporte.infrastructure.entrypoints.util;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.reporte.microservice_reporte.infrastructure.entrypoints.dto.BootcampReportRequestDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class APIResponse {
    private String code;
    private String message;
    private String identifier;
    private String date;
    private BootcampReportRequestDTO data;
    private List<ErrorDTO> errors;
}
