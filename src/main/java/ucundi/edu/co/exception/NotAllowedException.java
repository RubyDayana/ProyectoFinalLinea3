package ucundi.edu.co.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class NotAllowedException extends Exception{
	private static final long serialVersionUID = 1L;

	public NotAllowedException(String message) {
		super(message);
	}
}
