package com.msvc.security.authservice.service;

import com.msvc.security.authservice.dto.AuthUserDto;
import com.msvc.security.authservice.dto.NewUserDto;
import com.msvc.security.authservice.dto.RequestDto;
import com.msvc.security.authservice.dto.TokenDto;
import com.msvc.security.authservice.entity.AuthUser;
import com.msvc.security.authservice.repository.AuthUserRepository;
import com.msvc.security.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
private JwtProvider jwtProvider;

    public AuthUser save(NewUserDto authUserDto){
        Optional<AuthUser> user = authUserRepository.findByUsername(authUserDto.getUsername());
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(authUserDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .username(authUserDto.getUsername())
                .password(password)
                .rol(authUserDto.getRole())
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUsername(authUserDto.getUsername());
        if (!user.isPresent())
            return null;
        if (passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token, RequestDto requestDto){
        if(!jwtProvider.validateToken(token, requestDto))
                return null;
        String username = jwtProvider.getUsernameFromToken(token);
        if(!authUserRepository.findByUsername(username).isPresent())
            return null;
        return new TokenDto(token);
    }
   }

