package io.swagger.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 2400973038332633651L;

	public ResourceBadRequestException(String message) {
		super(message);
	}
}
