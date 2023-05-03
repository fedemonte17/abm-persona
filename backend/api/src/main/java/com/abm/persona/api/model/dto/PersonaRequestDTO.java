package com.abm.persona.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonaRequestDTO {

    @NotEmpty(message = "{nombre_not_empty}")
    @Size(min = 1 , max = 255)
    @Nullable
    private String nombre;

    @NotEmpty(message = "{apellido_not_empty}")
    @Size(min = 1 , max = 255)
    @Nullable
    private String apellido;

    @Nullable
    @JsonProperty("numero_documento")
    private Integer numeroDocumento;

    @NotEmpty(message = "{fechaNacimiento_not_empty}")
    @Size(min = 1 , max = 255)
    @Nullable
    @JsonProperty("fecha_nacimiento")
    private String fechaNacimiento;

    @Nullable
    @JsonProperty("tipo_documento")
    private TipoDocumentoRequestDTO tipoDocumento;
}
