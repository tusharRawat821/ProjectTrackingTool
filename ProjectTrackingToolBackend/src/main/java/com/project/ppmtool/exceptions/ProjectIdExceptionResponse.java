package com.project.ppmtool.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * We will use this POJO in our error handler and 
 * serialized it into json response whenever a ProjectIdException is thrown.
 * @author tushar
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class ProjectIdExceptionResponse {
	private String projectIdentifier;
}
