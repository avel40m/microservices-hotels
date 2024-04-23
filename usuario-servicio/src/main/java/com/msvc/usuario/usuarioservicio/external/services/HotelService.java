package com.msvc.usuario.usuarioservicio.external.services;

import com.msvc.usuario.usuarioservicio.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hotel-service")
public interface HotelService {

    @GetMapping("/api/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

}
