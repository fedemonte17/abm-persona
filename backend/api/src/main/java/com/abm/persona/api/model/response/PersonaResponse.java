package com.abm.persona.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonaResponse {

    private Integer id;

    private String nombre;

    private String apellido;

    private Integer numeroDocumento;

    private Instant fechaNacimiento;

    private TipoDocumentoResponse tipoDocumento;

}
