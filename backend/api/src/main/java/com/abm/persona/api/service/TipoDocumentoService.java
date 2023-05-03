package com.abm.persona.api.service;

import com.abm.persona.api.model.request.TipoDocumentoRequest;
import com.abm.persona.api.model.response.TipoDocumentoResponse;

import java.util.List;

public interface TipoDocumentoService {

    List<TipoDocumentoResponse> findAll();

    TipoDocumentoResponse create(TipoDocumentoRequest tipoDocumentoRequest);

    TipoDocumentoResponse update(TipoDocumentoRequest tipoDocumentoRequest, Integer idTipoDocumento);

    void delete(Integer idTipoDocumento);
}
