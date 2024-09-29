package br.com.projeto.crud.controller.advice;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.projeto.crud.model.DTO.ResponseDTO;
import br.com.projeto.crud.model.DTO.ResponseGenerator;
import br.com.projeto.crud.utils.Utils;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	private static final Logger LOG = Utils.createLoggerSize30(ExceptionHandlerAdvice.class);

	private static final String MSG_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
	private static final String MSG_BAD_REQUEST = HttpStatus.BAD_REQUEST.getReasonPhrase();

	public ExceptionHandlerAdvice() {
		LOG.info("-- LOAD BEAN -- >> {}", ExceptionHandlerAdvice.class.getSimpleName());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ResponseDTO> handleGenericException(Exception ex) {

		ResponseDTO body = ResponseGenerator.responseFail(ex.getLocalizedMessage(), MSG_INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ResponseDTO> handleNullPointerException(NullPointerException ex) {

		ResponseDTO body = ResponseGenerator.responseFail(ex.getLocalizedMessage(), MSG_BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {

		ResponseDTO body = ResponseGenerator.responseFail(
				ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList(),
				"Problema em {" + ex.getBindingResult().getObjectName() + "}");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

}
