package com.abm.persona.api.mapper;

import com.abm.persona.api.model.Persona;
import com.abm.persona.api.model.request.PersonaRequest;
import com.abm.persona.api.model.response.PersonaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TipoDocumentoMapper.class})
public interface PersonaMapper {


    Persona toModel(PersonaRequest request);

    PersonaResponse toResponse(Persona persona);

    List<PersonaResponse> toResponseList(List<Persona> persona);
}
