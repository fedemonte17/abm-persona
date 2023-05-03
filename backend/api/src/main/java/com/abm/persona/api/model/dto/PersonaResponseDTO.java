package com.abm.persona.api.model.dto;

import com.abm.persona.api.common.DateFormatSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonaResponseDTO {

    private Integer id;

    private String nombre;

    private String apellido;

    @JsonProperty("numero_documento")
    private Integer numeroDocumento;

    @JsonProperty("fecha_nacimiento")
    @JsonSerialize(using = DateFormatSerializer.class)
    private Instant fechaNacimiento;

    @JsonProperty("tipo_documento")
    private TipoDocumentoResponseDTO tipoDocumento;

}
