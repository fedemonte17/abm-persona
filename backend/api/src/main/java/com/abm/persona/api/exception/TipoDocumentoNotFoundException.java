package com.abm.persona.api.exception;

public class TipoDocumentoNotFoundException extends RuntimeException {

	private static final String key = "TIPO_DOCUMENTO_NOT_FOUND";

	private static final long serialVersionUID = 1L;

	public TipoDocumentoNotFoundException() {
		super(key);
	}

}
