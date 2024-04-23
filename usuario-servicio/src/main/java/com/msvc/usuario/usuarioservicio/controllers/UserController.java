package com.msvc.usuario.usuarioservicio.controllers;

import com.msvc.usuario.usuarioservicio.entity.User;
import com.msvc.usuario.usuarioservicio.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User request) {
        var user = userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        var user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        var users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception exception){
        log.info("The back is execute by cause server is inactive: " + exception.getMessage());
        var user = User
                .builder()
                .email("root@example.com")
                .name("root")
                .information("User created for default by service not found")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
