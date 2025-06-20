package com.reporte.microservice_reporte.config;

import com.reporte.microservice_reporte.domain.api.IBootcampReportServicePort;
import com.reporte.microservice_reporte.domain.spi.IBootcampReportPersistencePort;
import com.reporte.microservice_reporte.domain.usecase.BootcampReportUseCase;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.BootcampReportPersistenceAdapter;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.mapper.IBootcampReportEntityMapper;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.repository.IBootcampReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCasesConfig {
        private final IBootcampReportRepository bootcampPersonRepository;
        private final IBootcampReportEntityMapper bootcampReportEntityMapper;


        @Bean
        public IBootcampReportPersistencePort bootcampReportPersistencePort() {
                return new BootcampReportPersistenceAdapter(bootcampPersonRepository, bootcampReportEntityMapper);
        }

        @Bean
        public IBootcampReportServicePort bootcampReportServicePort(
                IBootcampReportPersistencePort persistencePort

        ) {
                return new BootcampReportUseCase(persistencePort );
        }


}