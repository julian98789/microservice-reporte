package com.reporte.microservice_reporte.infrastructure.entrypoints.handler;

import com.reporte.microservice_reporte.domain.api.IBootcampReportServicePort;
import com.reporte.microservice_reporte.domain.enums.TechnicalMessage;
import com.reporte.microservice_reporte.domain.exceptions.BusinessException;
import com.reporte.microservice_reporte.domain.exceptions.TechnicalException;
import com.reporte.microservice_reporte.infrastructure.entrypoints.dto.BootcampReportRequestDTO;
import com.reporte.microservice_reporte.infrastructure.entrypoints.mapper.IBootcampReportMapper;
import com.reporte.microservice_reporte.infrastructure.entrypoints.util.APIResponse;
import com.reporte.microservice_reporte.infrastructure.entrypoints.util.ErrorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootcampReportHandler {

    private final IBootcampReportServicePort useCase;
    private final IBootcampReportMapper mapper;

    public Mono<ServerResponse> saveReport(ServerRequest request) {
        return request.bodyToMono(BootcampReportRequestDTO.class)
                .map(mapper::toModel)
                .flatMap(useCase::saveBootcampReport)
                .flatMap(saved -> ServerResponse.status(HttpStatus.CREATED).bodyValue(saved))
                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        ex.getTechnicalMessage(),
                        List.of(ErrorDTO.builder()
                                .code(ex.getTechnicalMessage().getCode())
                                .message(ex.getMessage())
                                .param(ex.getTechnicalMessage().getParam())
                                .build())))
                .onErrorResume(TechnicalException.class, ex -> buildErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getTechnicalMessage(),
                        List.of(ErrorDTO.builder()
                                .code(ex.getTechnicalMessage().getCode())
                                .message(ex.getMessage())
                                .param(ex.getTechnicalMessage().getParam())
                                .build())))
                .onErrorResume(ex -> buildErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        TechnicalMessage.INTERNAL_ERROR,
                        List.of(ErrorDTO.builder()
                                .code(TechnicalMessage.INTERNAL_ERROR.getCode())
                                .message(ex.getMessage())
                                .build())));
    }

    private Mono<ServerResponse> buildErrorResponse(HttpStatus httpStatus, TechnicalMessage error,
                                                    List<ErrorDTO> errors) {
        return Mono.defer(() -> {
            APIResponse apiErrorResponse = APIResponse
                    .builder()
                    .code(error.getCode())
                    .message(error.getMessage())
                    .date(Instant.now().toString())
                    .errors(errors)
                    .build();
            return ServerResponse.status(httpStatus).bodyValue(apiErrorResponse);
        });
    }
}