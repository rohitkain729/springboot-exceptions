package com.app.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.entity.ErrorDetails;
import com.app.error.TouristNotFoundException;

@RestControllerAdvice
public class TouristAdviceController {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetails> handlerIllegalArgumentException(IllegalArgumentException iae) {
		ErrorDetails de = new ErrorDetails(LocalDateTime.now(), 502, iae.getMessage());
		return new ResponseEntity<ErrorDetails>(de, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllException(Exception e) {
		ErrorDetails de = new ErrorDetails(LocalDateTime.now(), 500, e.getMessage());
		System.out.println(e.getMessage());
		return new ResponseEntity<ErrorDetails>(de, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	HttpMessageNotReadableException:

	@ExceptionHandler(TouristNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTouristNotFoundExceptionException(TouristNotFoundException e) {
		ErrorDetails de = new ErrorDetails(LocalDateTime.now(), 503, e.getMessage());
		return new ResponseEntity<ErrorDetails>(de, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
