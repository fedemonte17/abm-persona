package com.abm.persona.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoDocumentoResponseDTO {

    private Integer id;

    @JsonProperty("nombre_tipo_documento")
    private String nombre;

}
