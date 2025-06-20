package com.reporte.microservice_reporte.infrastructure.entrypoints;


import com.reporte.microservice_reporte.infrastructure.entrypoints.dto.BootcampReportRequestDTO;
import com.reporte.microservice_reporte.infrastructure.entrypoints.handler.BootcampReportHandler;
import com.reporte.microservice_reporte.infrastructure.entrypoints.util.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Operation(
            summary = "Save a bootcamp report",
            description = "Creates and sends a report with bootcamp statistics to the report service.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = BootcampReportRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Report successfully submitted",
                            content = @Content(schema = @Schema(implementation = APIResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(schema = @Schema(implementation = APIResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(schema = @Schema(implementation = APIResponse.class)))
            }
    )
    @Bean
    public RouterFunction<ServerResponse> bootcampReportRoutes(BootcampReportHandler handler) {
        return route(POST("/report/bootcamp"), handler::saveReport);
    }
}