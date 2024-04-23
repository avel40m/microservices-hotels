package com.msvc.usuario.usuarioservicio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Classification {
    private String classificationId;
    private String  userId;
    private String hotelId;
    private int classification;
    private String observation;
    private Hotel hotel;
}
