package com.project.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A @RestController may use @ExceptionHandler methods with a ResponseEntity return value to set the 
 * status and the body of the response, but this handler is going to be specific for that controller.
 * Such methods can also be declared in @ControllerAdvice classes to apply them globally/centralized.
 * thus the name ControllerAdvice, means advises the controller.
 * 
 * 
 * Alternative : @RestControllerAdvice is a composed annotation that is annotated with both @ControllerAdvice 
 * and @ResponseBody, which essentially means @ExceptionHandler methods are rendered to the response 
 * body through message conversion (versus view resolution or template rendering).
 *
 *Applications that implement global exception handling with error details in the response body should consider 
 *extending ResponseEntityExceptionHandler, which provides handling for exceptions that Spring MVC raises and provides 
 *hooks to customize the response body. To make use of this, create a subclass of ResponseEntityExceptionHandler, 
 *annotate it with @ControllerAdvice, override the necessary methods, and declare it as a Spring bean.
 */

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException exception, WebRequest request) {
		ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(exception.getMessage());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	 
}
