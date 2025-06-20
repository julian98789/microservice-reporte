package com.reporte.microservice_reporte.domain.usecase;

import com.reporte.microservice_reporte.domain.model.BootcampReport;
import com.reporte.microservice_reporte.domain.spi.IBootcampReportPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class BootcampReportUseCaseTest {

    @Mock
    private IBootcampReportPersistencePort persistencePort;

    private BootcampReportUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new BootcampReportUseCase(persistencePort);
    }

    @Test
    void saveBootcampReport_shouldReturnSavedReport() {
        BootcampReport report = new BootcampReport(
                "r1", 100L, "Bootcamp Name", "Description",
                LocalDate.of(2025, 1, 1), 10, 50L, 5L, 15L
        );

        when(persistencePort.save(report)).thenReturn(Mono.just(report));

        StepVerifier.create(useCase.saveBootcampReport(report))
                .expectNext(report)
                .verifyComplete();

        verify(persistencePort).save(report);
    }

    @Test
    void saveBootcampReport_shouldPropagateError() {
        BootcampReport report = new BootcampReport(
                "r2", 200L, "Another Bootcamp", "Details",
                LocalDate.of(2025, 6, 1), 8, 30L, 6L, 12L
        );

        RuntimeException error = new RuntimeException("Database error");
        when(persistencePort.save(report)).thenReturn(Mono.error(error));

        StepVerifier.create(useCase.saveBootcampReport(report))
                .expectErrorMatches(e -> e instanceof RuntimeException && e.getMessage().equals("Database error"))
                .verify();

        verify(persistencePort).save(report);
    }
}
