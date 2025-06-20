package com.reporte.microservice_reporte.infrastructure.entrypoints.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BootcampReportRequestDTO {
    private Long bootcampId;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;
    private Long registeredPersonCount;
    private Long capacityCount;
    private Long totalTechnologyCount;
}