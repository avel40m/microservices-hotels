package com.msvc.clasificacion.clasificacionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("classifications")
public class Classifications {
    @Id
    private String classificationId;
    private String userId;
    private String hotelId;
    private int classification;
    private String observation;
}
