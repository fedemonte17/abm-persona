package com.abm.persona.api.mapper;

import com.abm.persona.api.model.dto.TipoDocumentoRequestDTO;
import com.abm.persona.api.model.dto.TipoDocumentoResponseDTO;
import com.abm.persona.api.model.request.TipoDocumentoRequest;
import com.abm.persona.api.model.response.TipoDocumentoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoDocumentoDTOMapper {

    TipoDocumentoRequest toRequest(TipoDocumentoRequestDTO tipoDocumentoRequestDTO);

    TipoDocumentoResponseDTO toResponse(TipoDocumentoResponse tipoDocumentoResponse);

    List<TipoDocumentoResponseDTO> toResponseList(List<TipoDocumentoResponse> tipoDocumentoResponse);

}
