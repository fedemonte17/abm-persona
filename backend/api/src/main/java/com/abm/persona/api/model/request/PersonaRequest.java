package com.abm.persona.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonaRequest {

    private String nombre;

    private String apellido;

    private Integer numeroDocumento;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Instant fechaNacimiento;

    private TipoDocumentoRequest tipoDocumento;

}
