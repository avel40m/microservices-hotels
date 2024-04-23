package com.msvc.usuario.usuarioservicio.service;

import com.msvc.usuario.usuarioservicio.entity.Classification;
import com.msvc.usuario.usuarioservicio.entity.Hotel;
import com.msvc.usuario.usuarioservicio.entity.User;
import com.msvc.usuario.usuarioservicio.exceptions.ResourcesNotFoundException;
import com.msvc.usuario.usuarioservicio.external.services.HotelService;
import com.msvc.usuario.usuarioservicio.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        var randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found with id: " + userId));

        Classification[] classificationsUser =
                restTemplate.getForObject("http://clasificacion-service/api/classification/user/" + user.getUserId(), Classification[].class);
        List<Classification> classifications = Arrays.stream(classificationsUser).collect(Collectors.toList());

        List<Classification> classificationList = classifications.stream().map(classification -> {
            Hotel hotel = hotelService.getHotel(classification.getHotelId());

            classification.setHotel(hotel);
            return classification;
        }).collect(Collectors.toList());

        user.setClassificationList(classificationList);
        return user;
    }
}
