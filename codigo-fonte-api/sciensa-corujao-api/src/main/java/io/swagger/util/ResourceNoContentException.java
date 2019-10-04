package io.swagger.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ResourceNoContentException extends RuntimeException {

	private static final long serialVersionUID = -4558576855393750221L;

	public ResourceNoContentException(String message) {
		super(message);
	}
}
