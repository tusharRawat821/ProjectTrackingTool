package com.project.ppmtool.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {
	/**
	 * Returns ResponseEntity<Map> with body as map of field errors.
	 * This method only handles the validation errors enforced by javax.validation.* annotations.
	 * if there's some @Column validations that's needs to be handled at DB level as that's hibernate/JPA exception
	 * example {"field" : "errorMsg",..}
	 * @param result
	 */
	public ResponseEntity<?> mapValidationError(BindingResult result) {
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error: result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		return null;
	}
	
}
