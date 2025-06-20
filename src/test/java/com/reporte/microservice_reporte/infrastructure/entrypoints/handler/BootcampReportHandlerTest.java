package com.reporte.microservice_reporte.infrastructure.entrypoints.handler;

import com.reporte.microservice_reporte.domain.api.IBootcampReportServicePort;
import com.reporte.microservice_reporte.domain.enums.TechnicalMessage;
import com.reporte.microservice_reporte.domain.exceptions.BusinessException;
import com.reporte.microservice_reporte.domain.exceptions.TechnicalException;
import com.reporte.microservice_reporte.domain.model.BootcampReport;
import com.reporte.microservice_reporte.infrastructure.entrypoints.dto.BootcampReportRequestDTO;
import com.reporte.microservice_reporte.infrastructure.entrypoints.mapper.IBootcampReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BootcampReportHandlerTest {

    @Mock
    private IBootcampReportServicePort servicePort;

    @Mock
    private IBootcampReportMapper mapper;

    private BootcampReportHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new BootcampReportHandler(servicePort, mapper);
    }

    @Test
    void saveReport_success() {
        ServerRequest request = mock(ServerRequest.class);
        BootcampReportRequestDTO dto = new BootcampReportRequestDTO(
                1L, "Bootcamp A", "Descripción", LocalDate.of(2024, 1, 1), 5,
                50L, 10L, 20L
        );

        BootcampReport report = new BootcampReport(
                "abc123", 1L, "Bootcamp A", "Descripción",
                LocalDate.of(2024, 1, 1), 5,
                50L, 10L, 20L
        );

        when(request.bodyToMono(BootcampReportRequestDTO.class)).thenReturn(Mono.just(dto));
        when(mapper.toModel(dto)).thenReturn(report);
        when(servicePort.saveBootcampReport(report)).thenReturn(Mono.just(report));

        ServerResponse response = handler.saveReport(request).block();

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.statusCode());
    }

    @Test
    void saveReport_businessException() {
        ServerRequest request = mock(ServerRequest.class);
        BootcampReportRequestDTO dto = new BootcampReportRequestDTO(
                1L, "Bootcamp A", "Descripción", LocalDate.of(2024, 1, 1), 5,
                50L, 10L, 20L
        );

        BootcampReport report = new BootcampReport(
                "abc123", 1L, "Bootcamp A", "Descripción",
                LocalDate.of(2024, 1, 1), 5,
                50L, 10L, 20L
        );

        when(request.bodyToMono(BootcampReportRequestDTO.class)).thenReturn(Mono.just(dto));
        when(mapper.toModel(dto)).thenReturn(report);
        when(servicePort.saveBootcampReport(report))
                .thenReturn(Mono.error(new BusinessException(TechnicalMessage.INTERNAL_ERROR)));

        ServerResponse response = handler.saveReport(request).block();

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode());
    }

    @Test
    void saveReport_technicalException() {
        ServerRequest request = mock(ServerRequest.class);
        BootcampReportRequestDTO dto = new BootcampReportRequestDTO(
                1L, "Bootcamp A", "Descripción", LocalDate.of(2024, 1, 1), 5,
                50L, 10L, 20L
        );

        BootcampReport report = new BootcampReport(
                "abc123", 1L, "Bootcamp A", "Descripción",
                LocalDate.of(2024, 1, 1), 5,
                50L, 10L, 20L
        );

        when(request.bodyToMono(BootcampReportRequestDTO.class)).thenReturn(Mono.just(dto));
        when(mapper.toModel(dto)).thenReturn(report);
        when(servicePort.saveBootcampReport(report))
                .thenReturn(Mono.error(new TechnicalException(TechnicalMessage.INTERNAL_ERROR)));

        ServerResponse response = handler.saveReport(request).block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.statusCode());
    }

    @Test
    void saveReport_unexpectedException() {
        ServerRequest request = mock(ServerRequest.class);
        BootcampReportRequestDTO dto = new BootcampReportRequestDTO(
                1L, "Bootcamp A", "Descripción", LocalDate.of(2024, 1, 1), 5,
                50L, 10L, 20L
        );

        BootcampReport report = new BootcampReport(
                "abc123", 1L, "Bootcamp A", "Descripción",
                LocalDate.of(2024, 1, 1), 5,
                50L, 10L, 20L
        );

        when(request.bodyToMono(BootcampReportRequestDTO.class)).thenReturn(Mono.just(dto));
        when(mapper.toModel(dto)).thenReturn(report);
        when(servicePort.saveBootcampReport(report))
                .thenReturn(Mono.error(new RuntimeException("Unexpected")));

        ServerResponse response = handler.saveReport(request).block();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.statusCode());
    }
}
