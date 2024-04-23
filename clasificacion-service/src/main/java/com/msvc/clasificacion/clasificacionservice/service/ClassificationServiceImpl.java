package com.msvc.clasificacion.clasificacionservice.service;

import com.msvc.clasificacion.clasificacionservice.entities.Classifications;
import com.msvc.clasificacion.clasificacionservice.repository.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService{

    @Autowired
    private ClassificationRepository classificationRepository;

    @Override
    public Classifications saveClassifications(Classifications classifications) {
        return classificationRepository.save(classifications);
    }

    @Override
    public List<Classifications> getClassifications() {
        return classificationRepository.findAll();
    }

    @Override
    public List<Classifications> getClassificationsByUserId(String userId) {
        return classificationRepository.findByUserId(userId);
    }

    @Override
    public List<Classifications> getClassificationsByHotelId(String hotelId) {
        return classificationRepository.findByHotelId(hotelId);
    }
}
