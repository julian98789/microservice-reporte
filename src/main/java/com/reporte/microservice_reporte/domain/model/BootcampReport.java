package com.reporte.microservice_reporte.domain.model;

import java.time.LocalDate;

public record BootcampReport(
        String id,
        Long bootcampId,
        String name,
        String description,
        LocalDate releaseDate,
        Integer duration,
        Long registeredPersonCount,
        Long capacityCount,
        Long totalTechnologyCount
) {}