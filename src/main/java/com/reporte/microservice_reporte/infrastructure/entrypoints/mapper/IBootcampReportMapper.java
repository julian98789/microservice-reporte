package com.reporte.microservice_reporte.infrastructure.entrypoints.mapper;


import com.reporte.microservice_reporte.domain.model.BootcampReport;
import com.reporte.microservice_reporte.infrastructure.entrypoints.dto.BootcampReportRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBootcampReportMapper {
    BootcampReport toModel(BootcampReportRequestDTO dto);
}