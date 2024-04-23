package com.msvc.usuario.usuarioservicio.external.services;

import com.msvc.usuario.usuarioservicio.entity.Classification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "clasificacion-service")
public interface ClassificationService {

    @PostMapping
    public ResponseEntity<Classification> saveClassification(Classification classification);
    @PostMapping("/classifications/{classificationId}")
    public ResponseEntity<Classification> updateClassification(@PathVariable String classificationId, Classification classification);
    @DeleteMapping("/classifications/{classificationId}")
    public void deleteClassification(@PathVariable String classificationId);

}
