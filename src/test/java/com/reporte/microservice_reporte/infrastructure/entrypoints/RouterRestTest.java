package com.reporte.microservice_reporte.infrastructure.entrypoints;

import com.reporte.microservice_reporte.infrastructure.entrypoints.handler.BootcampReportHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class RouterRestTest {

    @Mock
    private BootcampReportHandler bootcampReportHandler;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        RouterRest routerRest = new RouterRest();
        webTestClient = WebTestClient.bindToRouterFunction(
                routerRest.bootcampReportRoutes(bootcampReportHandler)
        ).build();

        lenient().when(bootcampReportHandler.saveReport(any())).thenReturn(ServerResponse.ok().build());
    }

    @Test
    void testSaveBootcampReportRoute() {
        webTestClient.post().uri("/report/bootcamp")
                .exchange()
                .expectStatus().isOk();
    }
}
