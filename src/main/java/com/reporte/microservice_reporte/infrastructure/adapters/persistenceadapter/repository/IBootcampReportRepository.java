package com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.repository;


import com.reporte.microservice_reporte.infrastructure.adapters.persistenceadapter.entity.BootcampReportEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBootcampReportRepository extends ReactiveMongoRepository<BootcampReportEntity, String> {}
