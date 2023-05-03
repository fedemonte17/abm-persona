package com.abm.persona.api.controller;

import com.abm.persona.api.mapper.PersonaDTOMapper;
import com.abm.persona.api.model.Persona;
import com.abm.persona.api.model.dto.PersonaRequestDTO;
import com.abm.persona.api.model.dto.PersonaResponseDTO;
import com.abm.persona.api.model.request.PersonaRequest;
import com.abm.persona.api.model.response.PageDataPersona;
import com.abm.persona.api.model.response.PersonaResponse;
import com.abm.persona.api.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;


@RestController
@CrossOrigin(origins = "http://localhost:4000")
@RequestMapping(value = "/api/persona/", consumes = {MediaType.ALL_VALUE})
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaDTOMapper personaDTOMapper;

    @ResponseBody
    @GetMapping("search")
    public ResponseEntity<?> search(
            Pageable pageRequest,
            @RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(value = "id_tipoDocumento", required = false) Integer idTipoDocumento
    ) {
        try{
            Page<Persona> personaPage = personaService.search(nombre, idTipoDocumento, pageRequest);
            Page<PersonaResponseDTO> responseDTOS = personaPage.map(new Function<Persona, PersonaResponseDTO>() {
                @Override
                public PersonaResponseDTO apply(Persona personaResponse) {
                    return personaDTOMapper.toResponsePerson(personaResponse);
                }
            });
            return new ResponseEntity<>(buildResponsePersonaPage(responseDTOS), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se obtuvieron datos de personas", HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping("get-persona/{idPersona}")
    public ResponseEntity<?> getPersonasById(
            @PathVariable("idPersona")
            Integer idPersona
    ) {
        try{
            PersonaResponse person = personaService.findById(idPersona);
            PersonaResponseDTO response = personaDTOMapper.toResponse(person);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseEntity<?> create(
            @RequestBody PersonaRequestDTO personaRequestDTO
    ) {
        try{
            PersonaRequest request = personaDTOMapper.toRequest(personaRequestDTO);
            PersonaResponse personaResponse = personaService.create(request);
            PersonaResponseDTO response = personaDTOMapper.toResponse(personaResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se crear la persona" +
                    personaRequestDTO.getNombre() + " " + personaRequestDTO.getApellido()
                    , HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @PutMapping("update/{idPersona}")
    public ResponseEntity<?> update(
            @PathVariable("idPersona")
            Integer idPersona,
            @RequestBody PersonaRequestDTO personaRequestDTO
    ) {
        try{
            PersonaRequest request = personaDTOMapper.toRequest(personaRequestDTO);
            PersonaResponse personaResponse = personaService.update(request, idPersona);
            PersonaResponseDTO response = personaDTOMapper.toResponse(personaResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se actualizo la persona" +
                    personaRequestDTO.getNombre() + " " + personaRequestDTO.getApellido()
                    , HttpStatus.BAD_REQUEST);        }
    }

    @ResponseBody
    @DeleteMapping("delete/{idPersona}")
    public ResponseEntity<?> delete(
            @PathVariable("idPersona")
            Integer idPersona
    ) {
         try{
             personaService.delete(idPersona);
             return new ResponseEntity<>("Persona con id " + idPersona + " eliminada", HttpStatus.OK);

         }catch (Exception e){
             return new ResponseEntity<>("No se elimino la persona con id: " + idPersona, HttpStatus.NOT_FOUND);
         }
    }

    private PageDataPersona buildResponsePersonaPage(Page<PersonaResponseDTO> response) {
        PageDataPersona pageData = new PageDataPersona();
        pageData.setPage(response.getNumber());
        pageData.setSize(response.getSize());
        pageData.setTotal(response.getTotalElements());
        pageData.setItems(response.getContent());
        return pageData;
    }
}
