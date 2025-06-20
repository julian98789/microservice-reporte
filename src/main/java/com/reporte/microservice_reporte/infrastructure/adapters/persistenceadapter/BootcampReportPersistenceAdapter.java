package com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter;

import com.reporte.microservice_reporte.domain.spi.IBootcampReportPersistencePort;
import com.reporte.microservice_reporte.domain.model.BootcampReport;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.mapper.IBootcampReportEntityMapper;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.repository.IBootcampReportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampReportPersistenceAdapter implements IBootcampReportPersistencePort {

    private final IBootcampReportRepository repository;
    private final IBootcampReportEntityMapper mapper;

    @Override
    public Mono<BootcampReport> save(BootcampReport report) {
        return repository.save(mapper.toEntity(report))
                .map(mapper::toModel);
    }
}