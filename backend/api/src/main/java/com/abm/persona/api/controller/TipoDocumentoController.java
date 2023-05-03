package com.abm.persona.api.controller;

import com.abm.persona.api.mapper.TipoDocumentoDTOMapper;
import com.abm.persona.api.model.dto.TipoDocumentoRequestDTO;
import com.abm.persona.api.model.dto.TipoDocumentoResponseDTO;
import com.abm.persona.api.model.request.TipoDocumentoRequest;
import com.abm.persona.api.model.response.TipoDocumentoResponse;
import com.abm.persona.api.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
@RequestMapping(value = "/api/tipo-documento/", consumes = { MediaType.ALL_VALUE })
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @Autowired
    private TipoDocumentoDTOMapper tipoDocumentoDTOMapper;

    @GetMapping("get-tipo-documento")
    public ResponseEntity<?> getTipoDocumentoAll(){
        try{
            List<TipoDocumentoResponse> all = tipoDocumentoService.findAll();
            List<TipoDocumentoResponseDTO> responseList = tipoDocumentoDTOMapper.toResponseList(all);
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se obtuvieron datos de tipo documento", HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseEntity<?> create(
            @RequestBody TipoDocumentoRequestDTO tipoDocumentoRequestDTO
    ){
        try {
            TipoDocumentoRequest request = tipoDocumentoDTOMapper.toRequest(tipoDocumentoRequestDTO);
            TipoDocumentoResponse tipoDocumentoResponse = tipoDocumentoService.create(request);
            TipoDocumentoResponseDTO response = tipoDocumentoDTOMapper.toResponse(tipoDocumentoResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se crear el tipo de documento: " +
                    tipoDocumentoRequestDTO.getNombre()
                    , HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @PutMapping("update/{idTipoDocumento}")
    public ResponseEntity<?> update(
            @PathVariable("idTipoDocumento")
            Integer idTipoDocumento,
            @RequestBody TipoDocumentoRequestDTO tipoDocumentoRequestDTO
    ){
        try{
            TipoDocumentoRequest request = tipoDocumentoDTOMapper.toRequest(tipoDocumentoRequestDTO);
            TipoDocumentoResponse tipoDocumentoResponse = tipoDocumentoService.update(request, idTipoDocumento);
            TipoDocumentoResponseDTO response = tipoDocumentoDTOMapper.toResponse(tipoDocumentoResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se actualizo el tipo de documento: " +
                    tipoDocumentoRequestDTO.getNombre()
                    , HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @DeleteMapping("delete/{idTipoDocumento}")
    public ResponseEntity<?> delete(
            @PathVariable("idTipoDocumento")
            Integer idTipoDocumento
    ){
        try{
            tipoDocumentoService.delete(idTipoDocumento);
            return new ResponseEntity<>("Tipo de Documento con id "+ idTipoDocumento + " eliminado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se elimino el Tipo de Documento con id: " + idTipoDocumento, HttpStatus.NOT_FOUND);
        }

    }
}
