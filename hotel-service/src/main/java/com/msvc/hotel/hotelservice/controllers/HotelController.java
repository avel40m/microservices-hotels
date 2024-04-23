package com.msvc.hotel.hotelservice.controllers;

import com.msvc.hotel.hotelservice.entity.Hotel;
import com.msvc.hotel.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hotelService.saveHotel(hotel));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(hotelService.getHotels());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> findHotelId(@PathVariable String hotelId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(hotelService.getHotelById(hotelId));
    }
}
