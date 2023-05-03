package com.abm.persona.api.mapper;

import com.abm.persona.api.model.Persona;
import com.abm.persona.api.model.dto.PersonaRequestDTO;
import com.abm.persona.api.model.dto.PersonaResponseDTO;
import com.abm.persona.api.model.request.PersonaRequest;
import com.abm.persona.api.model.response.PersonaResponse;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = {TipoDocumentoDTOMapper.class})
public interface PersonaDTOMapper {

    PersonaRequest toRequest(PersonaRequestDTO personaRequestDTO);

    default Instant mapfechaNacimiento(String fechaNacimiento){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Instant instant = LocalDate.parse(fechaNacimiento, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return instant;
    }

    PersonaResponseDTO toResponse(PersonaResponse personaResponse);

    PersonaResponseDTO toResponsePerson(Persona persona);

    List<PersonaResponseDTO> toResponseList(List<PersonaResponse> personaResponse);

}
