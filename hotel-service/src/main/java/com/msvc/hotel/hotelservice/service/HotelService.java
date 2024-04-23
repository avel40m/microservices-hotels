package com.msvc.hotel.hotelservice.service;

import com.msvc.hotel.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotel);
    List<Hotel> getHotels();
    Hotel getHotelById(String hotelId);
}
