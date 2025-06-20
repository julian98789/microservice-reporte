package com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.mapper;

import com.reporte.microservice_reporte.domain.model.BootcampReport;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.entity.BootcampReportEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBootcampReportEntityMapper {
    BootcampReportEntity toEntity(BootcampReport model);
    BootcampReport toModel(BootcampReportEntity entity);
}