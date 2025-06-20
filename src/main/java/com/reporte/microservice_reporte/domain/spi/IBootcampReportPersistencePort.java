package com.reporte.microservice_reporte.domain.spi;

import com.reporte.microservice_reporte.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface IBootcampReportPersistencePort {

    Mono<BootcampReport> save(BootcampReport report);
}
