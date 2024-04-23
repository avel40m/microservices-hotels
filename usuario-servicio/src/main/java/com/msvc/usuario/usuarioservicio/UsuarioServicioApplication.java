package com.msvc.usuario.usuarioservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsuarioServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioServicioApplication.class, args);
	}

}
