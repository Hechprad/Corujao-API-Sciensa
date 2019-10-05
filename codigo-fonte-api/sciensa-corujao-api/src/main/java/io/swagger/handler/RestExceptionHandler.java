package io.swagger.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.swagger.error.ResourceBadRequestDetails;
import io.swagger.error.ResourceBadRequestException;
import io.swagger.error.ResourceInternalServerErrorDetails;
import io.swagger.error.ResourceNotFoundDetails;
import io.swagger.error.ResourceNotFoundException;
import io.swagger.error.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionHandler {
	
	// NOT FOUND
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException){
		ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
			.newBuilder()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.type("Not Found")
			.timestamp(new Date().getTime())
			.detail(rnfException.getMessage())
			.devMessage("Exception lançada: " +  rnfException.getClass().getName())
			.build();
		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}
	
	// BAD REQUEST
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> handleResourceBadRequestException(ResourceBadRequestException rbrException){
		ResourceBadRequestDetails rbrDetails = ResourceBadRequestDetails.Builder
			.newBuilder()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.type("Bad Request")
			.timestamp(new Date().getTime())
			.detail(rbrException.getMessage())
			.devMessage("Exception lançada: " +  rbrException.getClass().getName())
			.build();
		return new ResponseEntity<>(rbrDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	// BAD REQUEST for exception "MethodArgumentNotValidException"
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException manvException){
		
		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
		// Separando os campos com erro por vírgula
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		// Separando os campos com erro por vírgula
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		
		ValidationErrorDetails vjDetails = ValidationErrorDetails.Builder
			.newBuilder()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.type("Not Found")
			.timestamp(new Date().getTime())
			.field(fields)
			.fieldMessage(fieldMessages)
			.detail("Valor incorreto, por favor coloque um valor válido.")
			.devMessage("Exception lançada: " + manvException.getClass().getName())
			.build();
		return new ResponseEntity<>(vjDetails, HttpStatus.BAD_REQUEST);
	}
	
	// INTERNAL SERVER ERROR for exception "EmptyResultDataAccessException"
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> emptyResultDataAccessException(EmptyResultDataAccessException erdaException){
		
		ResourceInternalServerErrorDetails vriseDetails = ResourceInternalServerErrorDetails.Builder
				.newBuilder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.type("Internal Server Error")
				.timestamp(new Date().getTime())
				.detail("ID especificado não cadastrado! - " + erdaException.getMessage())
				.devMessage("Exception lançada: " + erdaException.getClass().getName())
				.build();
		return new ResponseEntity<>(vriseDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
