package com.msvc.clasificacion.clasificacionservice.service;

import com.msvc.clasificacion.clasificacionservice.entities.Classifications;

import java.util.List;

public interface ClassificationService {
    Classifications saveClassifications(Classifications classifications);
    List<Classifications> getClassifications();
    List<Classifications> getClassificationsByUserId(String userId);
    List<Classifications> getClassificationsByHotelId(String hotelId);
}
