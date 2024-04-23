package com.msvc.usuario.usuarioservicio.repository;

import com.msvc.usuario.usuarioservicio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
