package com.abm.persona.api.service;

import com.abm.persona.api.model.Persona;
import com.abm.persona.api.model.request.PersonaRequest;
import com.abm.persona.api.model.response.PersonaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonaService {


    Page<Persona> search(String nombre, Integer idTipoDocumento, Pageable pageable);

    PersonaResponse findById(Integer idPersona);

    PersonaResponse create(PersonaRequest personaRequest);

    PersonaResponse update(PersonaRequest personaRequest, Integer idPersona);

    void delete(Integer idPersona);
}
