package com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "bootcamp_report")
public class BootcampReportEntity {

    @Id
    private String id;
    private Long bootcampId;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;
    private Long registeredPersonCount;
    private Long capacityCount;
    private Long totalTechnologyCount;
}