package com.project.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * whenever this exception is returned ResponseStatus is going to be overridden as 400 bad request.
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {
	
	public ProjectIdException(String message) {
		super(message);
	}
}
