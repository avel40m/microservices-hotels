package com.msvc.usuario.usuarioservicio.service;

import com.msvc.usuario.usuarioservicio.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getUsers();
    User getUserById(String userId);
}
