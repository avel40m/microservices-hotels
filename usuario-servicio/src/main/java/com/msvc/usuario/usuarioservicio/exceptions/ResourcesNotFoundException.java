package com.msvc.usuario.usuarioservicio.exceptions;

public class ResourcesNotFoundException extends RuntimeException{
    public ResourcesNotFoundException() {
        super("Resource not found in the service");
    }

    public ResourcesNotFoundException(String message) {
        super(message);
    }

}
