package com.abm.persona.api.service.impl;

import com.abm.persona.api.exception.PersonaNotFoundException;
import com.abm.persona.api.mapper.PersonaMapper;
import com.abm.persona.api.model.Persona;
import com.abm.persona.api.model.request.PersonaRequest;
import com.abm.persona.api.model.response.PersonaResponse;
import com.abm.persona.api.repository.PersonaRepository;
import com.abm.persona.api.service.PersonaService;
import com.abm.persona.api.utils.EntityMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;


    @Override
    public Page<Persona> search(String nombre, Integer idTipoDocumento, Pageable pageable) {
       Page<Persona> personas  = personaRepository.search(nombre,idTipoDocumento, pageable);
        if(personas == null){
            return new PageImpl<>(Collections.emptyList(),pageable,0);
        }else {
            return personas;
        }
    }

    @Override
    public PersonaResponse findById(Integer idPersona) {
        Optional<Persona> person = personaRepository.findById(idPersona);
        if(!person.isPresent()){
           throw new PersonaNotFoundException();
        }
        return personaMapper.toResponse(person.get());
    }

    @Override
    public PersonaResponse create(PersonaRequest personaRequest) {
        Persona model = personaMapper.toModel(personaRequest);
        Persona persona = personaRepository.save(model);
        PersonaResponse response = personaMapper.toResponse(persona);
        return response;
    }

    @Override
    public PersonaResponse update(PersonaRequest personaRequest, Integer idPersona) {
        Optional<Persona> person = personaRepository.findById(idPersona);
        if(!person.isPresent()){
            throw new PersonaNotFoundException();
        }else{
            Persona persona = EntityMerger.mergePersona(person.get(), personaRequest);
            Persona save = personaRepository.save(persona);
            PersonaResponse response = personaMapper.toResponse(save);
            return response;
        }
    }

    @Override
    public void delete(Integer idPersona) {
        personaRepository.deleteById(idPersona);
    }

    private boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }
}
