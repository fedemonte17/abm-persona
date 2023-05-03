package com.abm.persona.api.model.dto;

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
public class TipoDocumentoRequestDTO {

    private Integer id;

    @NotEmpty(message = "{nombre_not_empty}")
    @Size(min = 1 , max = 255)
    @Nullable
    private String nombre;

}
