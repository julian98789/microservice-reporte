package com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter;

import com.reporte.microservice_reporte.domain.model.BootcampReport;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.entity.BootcampReportEntity;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.mapper.IBootcampReportEntityMapper;
import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.repository.IBootcampReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BootcampReportPersistenceAdapterTest {

    @Mock
    private IBootcampReportRepository repository;

    @Mock
    private IBootcampReportEntityMapper mapper;

    private BootcampReportPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new BootcampReportPersistenceAdapter(repository, mapper);
    }

    @Test
    void save_shouldMapAndPersistReport() {
        BootcampReport model = new BootcampReport(
                "123", 1L, "Bootcamp", "Desc",
                LocalDate.of(2024, 1, 1), 4,
                10L, 5L, 20L
        );

        BootcampReportEntity entity = new BootcampReportEntity();
        entity.setId("123");
        entity.setBootcampId(1L);
        entity.setName("Bootcamp");
        entity.setDescription("Desc");
        entity.setReleaseDate(LocalDate.of(2024, 1, 1));
        entity.setDuration(4);
        entity.setRegisteredPersonCount(10L);
        entity.setCapacityCount(5L);
        entity.setTotalTechnologyCount(20L);

        when(mapper.toEntity(model)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(Mono.just(entity));
        when(mapper.toModel(entity)).thenReturn(model);

        StepVerifier.create(adapter.save(model))
                .expectNext(model)
                .verifyComplete();

        verify(mapper).toEntity(model);
        verify(repository).save(entity);
        verify(mapper).toModel(entity);
    }
}
