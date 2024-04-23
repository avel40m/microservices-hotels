package com.msvc.clasificacion.clasificacionservice.repository;

import com.msvc.clasificacion.clasificacionservice.entities.Classifications;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationRepository extends MongoRepository<Classifications, String> {
    List<Classifications> findByUserId(String userId);
    List<Classifications> findByHotelId(String hotelId);
}
