package com.abm.persona.api.service.impl;

import com.abm.persona.api.exception.TipoDocumentoNotFoundException;
import com.abm.persona.api.mapper.TipoDocumentoMapper;
import com.abm.persona.api.model.TipoDocumento;
import com.abm.persona.api.model.request.TipoDocumentoRequest;
import com.abm.persona.api.model.response.TipoDocumentoResponse;
import com.abm.persona.api.repository.TipoDocumentoRepository;
import com.abm.persona.api.service.TipoDocumentoService;
import com.abm.persona.api.utils.EntityMerger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private TipoDocumentoMapper tipoDocumentoMapper;

    @Override
    public List<TipoDocumentoResponse> findAll() {
        List<TipoDocumento> all = tipoDocumentoRepository.findAll();
        List<TipoDocumentoResponse> responseList = tipoDocumentoMapper.toResponseList(all);
        return responseList;
    }

    @Override
    public TipoDocumentoResponse create(TipoDocumentoRequest tipoDocumentoRequest) {
        TipoDocumento model = tipoDocumentoMapper.toModel(tipoDocumentoRequest);
        TipoDocumento tipoDocumento = tipoDocumentoRepository.save(model);
        TipoDocumentoResponse response = tipoDocumentoMapper.toResponse(tipoDocumento);
        return response;
    }

    @Override
    public TipoDocumentoResponse update(TipoDocumentoRequest tipoDocumentoRequest, Integer idTipoDocumento) {
        Optional<TipoDocumento> optional = tipoDocumentoRepository.findById(idTipoDocumento);
        if(!optional.isPresent()){
            throw new TipoDocumentoNotFoundException();
        }else{
            TipoDocumento tipoDocumento = EntityMerger.mergeTipoDocumento(optional.get(), tipoDocumentoRequest);
            TipoDocumento save = tipoDocumentoRepository.save(tipoDocumento);
            TipoDocumentoResponse response = tipoDocumentoMapper.toResponse(save);
            return response;
        }
    }

    @Override
    public void delete(Integer idTipoDocumento) {
        tipoDocumentoRepository.deleteById(idTipoDocumento);

    }
}
