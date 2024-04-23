package com.msvc.security.authservice.controller;

import com.msvc.security.authservice.dto.AuthUserDto;
import com.msvc.security.authservice.dto.NewUserDto;
import com.msvc.security.authservice.dto.RequestDto;
import com.msvc.security.authservice.dto.TokenDto;
import com.msvc.security.authservice.entity.AuthUser;
import com.msvc.security.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto){
        TokenDto tokenDto = authService.login(authUserDto);
        if(authUserDto == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto){
        TokenDto tokenDto = authService.validate(token, requestDto);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDto authUserDto){
        AuthUser authUser = authService.save(authUserDto);
        if (authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }
}
