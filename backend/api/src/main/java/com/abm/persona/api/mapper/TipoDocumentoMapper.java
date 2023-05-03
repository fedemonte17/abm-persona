package com.abm.persona.api.mapper;

import com.abm.persona.api.model.TipoDocumento;
import com.abm.persona.api.model.request.TipoDocumentoRequest;
import com.abm.persona.api.model.response.TipoDocumentoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoDocumentoMapper {



    TipoDocumento toModel(TipoDocumentoRequest request);

    TipoDocumentoResponse toResponse(TipoDocumento tipoDocumento);

    List<TipoDocumentoResponse> toResponseList(List<TipoDocumento> tipoDocumento);
}
