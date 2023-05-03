package com.abm.persona.api.model.response;

import com.abm.persona.api.model.dto.PersonaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageDataPersona {

    private List<PersonaResponseDTO> items= null;

    private Integer page;

    private Integer size;

    private Long total;
}
