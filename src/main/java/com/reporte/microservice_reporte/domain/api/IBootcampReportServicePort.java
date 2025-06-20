package com.reporte.microservice_reporte.domain.api;

import com.reporte.microservice_reporte.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface IBootcampReportServicePort {

    Mono<BootcampReport> saveBootcampReport(BootcampReport report);

}
