package com.reporte.microservice_reporte.domain.usecase;

import com.reporte.microservice_reporte.domain.api.IBootcampReportServicePort;
import com.reporte.microservice_reporte.domain.model.BootcampReport;
import com.reporte.microservice_reporte.domain.spi.IBootcampReportPersistencePort;
import reactor.core.publisher.Mono;

public class BootcampReportUseCase implements IBootcampReportServicePort {

    private final IBootcampReportPersistencePort persistencePort;

    public BootcampReportUseCase(IBootcampReportPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Mono<BootcampReport> saveBootcampReport(BootcampReport report) {
        return persistencePort.save(report);
    }
}
