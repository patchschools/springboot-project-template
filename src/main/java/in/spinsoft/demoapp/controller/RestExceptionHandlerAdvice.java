package in.spinsoft.demoapp.controller;

import java.nio.file.AccessDeniedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import in.spinsoft.demoapp.dto.Message;
import in.spinsoft.demoapp.exception.ServiceException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class RestExceptionHandlerAdvice extends ExceptionHandlerExceptionResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandlerAdvice.class);

	@ResponseBody
	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<?> handleException(ServiceException exception) {
		Message message = new Message(exception.getMessage());
		LOGGER.error("ServiceException - {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}

	@ResponseBody
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<?> handleException1(AccessDeniedException exception) {
		Message message = new Message(exception.getMessage());
		LOGGER.error("AccessDeniedException - {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	}

	@ResponseBody
	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<?> handleException2(ExpiredJwtException exception) {

		Message message = new Message("Token Expired");
		LOGGER.error("ExpiredJwtException - {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	}

	@ResponseBody
	@ExceptionHandler(value = MalformedJwtException.class)
	public ResponseEntity<?> handleException2(MalformedJwtException exception) {

		Message message = new Message("Invalid Token");
		LOGGER.error("MalformedJwtException - {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	}

}