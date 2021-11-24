package ucundi.edu.co.exception;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Conflict;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandlerPer extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ConflictException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorConflictException(ConflictException e,
			WebRequest request) {
		
		System.out.print("Entron personal");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelNotFoundException(ModelNotFoundException e,
			WebRequest request) {
		
		System.out.print("Entron personal");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorBadRequestException(BadRequestException e,
			WebRequest request) {
		e.printStackTrace();
		System.out.print("Bad requuiste");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolation.class)
	public final ResponseEntity<ExceptionWrapper> manejadorConstraintViolation(ConstraintViolation e,
			WebRequest request) {
		e.printStackTrace();
		System.out.print("Constrain");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyModelException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorEmptyModelException(EmptyModelException e,
			WebRequest request) {
		e.printStackTrace();
		System.out.print("Empty");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotAllowedException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorNotAllowedException(NotAllowedException e,
			WebRequest request) {
		e.printStackTrace();
		System.out.print("Not allowed");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.METHOD_NOT_ALLOWED.value(),
				HttpStatus.METHOD_NOT_ALLOWED.toString(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(ArithmeticException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelArithmeticException(ArithmeticException e,
			WebRequest request) {
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Ha ocurrido un error", request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelException(Exception e, WebRequest request) {
		e.printStackTrace();
		System.out.print("Entro general");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Ha ocurrido un error", request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Entro 2");
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.METHOD_NOT_ALLOWED.value(),
				HttpStatus.METHOD_NOT_ALLOWED.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}

	// Argumentos no validos -- Por averfiguar
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// Se pueba enviando en el objeto un campo vacio
		ex.printStackTrace();
		System.out.print("Error argumentos");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				mennsaje(ex), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}
/**
 * Metodo para extrear los campos y mensajes de las validaciones
 * @param ex
 * @return mensaje error
 */
	private String mennsaje(MethodArgumentNotValidException ex) {
		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		final List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		System.out.print("ex normal"+ex.getMessage());
		StringBuilder errors = new StringBuilder();

		for (FieldError fieldError : fieldErrors) {
			errors.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("  "+" \n ");
		}

		for (ObjectError globalError : globalErrors) {
			errors.append(globalError.getObjectName()).append(": ").append(globalError.getDefaultMessage()).append("\n")
					;
		}
		return errors.toString();
	}
	
	
	
	

	// Por averfiguar
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		System.out.println("Entro 3");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		System.out.println("Entro 4");
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.NOT_FOUND);
	}

	
	

}
