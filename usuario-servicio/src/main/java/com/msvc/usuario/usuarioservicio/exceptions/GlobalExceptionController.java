package com.msvc.usuario.usuarioservicio.exceptions;

import com.msvc.usuario.usuarioservicio.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFound(ResourcesNotFoundException resourcesNotFoundException){

        var response = ApiResponse
                .builder()
                .message(resourcesNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
