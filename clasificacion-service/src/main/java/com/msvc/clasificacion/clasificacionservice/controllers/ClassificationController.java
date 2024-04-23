package com.msvc.clasificacion.clasificacionservice.controllers;

import com.msvc.clasificacion.clasificacionservice.entities.Classifications;
import com.msvc.clasificacion.clasificacionservice.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classification")
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @PostMapping
    public ResponseEntity<Classifications> saveClassifications(@RequestBody Classifications classification){
     return ResponseEntity
             .status(HttpStatus.CREATED)
             .body(classificationService.saveClassifications(classification));
    }

    @GetMapping
    public ResponseEntity<List<Classifications>> getAllClassifications(){
        return ResponseEntity.ok(classificationService.getClassifications());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Classifications>> getAllClassificationsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(classificationService.getClassificationsByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Classifications>> getAllClassificationsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(classificationService.getClassificationsByHotelId(hotelId));
    }
}
