package com.abm.persona.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PersonaNotFoundException extends RuntimeException {

	private static final String key = "PERSONA_NOT_FOUND";

	private static final long serialVersionUID = 1L;

	public PersonaNotFoundException() {
		super(key);
	}

	public PersonaNotFoundException(String message) {
		super(message);
	}

}
