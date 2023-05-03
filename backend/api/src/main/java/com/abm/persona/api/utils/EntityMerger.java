package com.abm.persona.api.utils;

import com.abm.persona.api.model.Persona;
import com.abm.persona.api.model.TipoDocumento;
import com.abm.persona.api.model.request.PersonaRequest;
import com.abm.persona.api.model.request.TipoDocumentoRequest;

public class EntityMerger {

    public static Persona mergePersona(Persona persona, PersonaRequest personaRequest){
        TipoDocumento tipoDocumento = new TipoDocumento();
        persona.setNombre(personaRequest.getNombre());
        persona.setApellido(personaRequest.getApellido());
        persona.setFechaNacimiento(personaRequest.getFechaNacimiento());
        persona.setNumeroDocumento(personaRequest.getNumeroDocumento());
        tipoDocumento.setId(personaRequest.getTipoDocumento().getId());
        tipoDocumento.setNombre(personaRequest.getTipoDocumento().getNombre());
        persona.setTipoDocumento(tipoDocumento);
        return persona;
    }

    public static TipoDocumento mergeTipoDocumento(TipoDocumento tipoDocumento, TipoDocumentoRequest tipoDocumentoRequest){
        tipoDocumento.setNombre(tipoDocumentoRequest.getNombre());
        return tipoDocumento;
    }
}
