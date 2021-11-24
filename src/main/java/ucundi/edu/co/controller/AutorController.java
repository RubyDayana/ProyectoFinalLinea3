package ucundi.edu.co.controller;


import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Conflict;

import ucundi.edu.co.entity.Autor;
import ucundi.edu.co.dto.AutorDto;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.service.IAutorService;

@PreAuthorize("hasAuthority('Autor')")
@RequestMapping("/autor")
@RestController
@Validated

public class AutorController {

	
	@Autowired
	private IAutorService service;
	
	  @GetMapping(value = "/gets/{i}/{nombre}/{correo}", produces =
	  "application/json") public ResponseEntity<?> obtenerdos(
	  
	  @PathVariable("i") @Min(value = 1, message =
	  "El código debe ser mayor o igual a 1") @Max(value = 100, message =
	  "El código debe ser menor o igual a 100") int i,
	  
	  @PathVariable("nombre") @Size(min = 3, max = 30, message =
	  "Este campo no puede tener menos de 3 o mas de 10 letras") String nombre,
	  
	  @PathVariable("correo") @Pattern(regexp =
	  "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." +
	  "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
	  "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]" +
	  "(?:[A-Za-z0-9-]*[A-Za-z0-9])?", message =
	  "El Correo que ingresó no es Válido") String correo) throws
	  ModelNotFoundException, Exception ,MethodArgumentNotValidException{
	  //autorDto autor = service.obtenerDos(i);
	  
	  return new ResponseEntity<>( HttpStatus.OK);
	  
	  }
	  
	  
	

		@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
		public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
			Page<Autor> listaAutor = service.retornarPaginado(page, size);
			return new ResponseEntity<Page<Autor>>(listaAutor, HttpStatus.OK);	
		}
		
		@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
		public ResponseEntity<?> retonarPaginado(Pageable page) {
			Page<Autor> listaAutor = service.retornarPaginado(page);
			return new ResponseEntity<Page<Autor>>(listaAutor, HttpStatus.OK);	
		}

		

		@GetMapping(value = "/obtenerById/{id}", produces = "application/json")
		public ResponseEntity<?> obtenerById(@PathVariable int id) throws ModelNotFoundException, Exception {
			Autor autor = service.retonarPorId(id);

			return new ResponseEntity<>(autor, HttpStatus.OK);

		}
		
		@GetMapping(value = "/obtenerByIdDetalle/{id}/{detalle}", produces = "application/json")
		public ResponseEntity<?> obtenerByIdByDetalle(@PathVariable int id,@PathVariable boolean detalle) throws ModelNotFoundException, Exception {
			Autor autor=service.retornarPorIdDetalle(id, detalle);

			return new ResponseEntity<>(autor, HttpStatus.OK);

		}

		@PostMapping(value = "/guardar")
		public ResponseEntity<?> guardar(@Valid @RequestBody Autor autor) throws ConflictException, ModelNotFoundException {
			
			service.guardar(autor);
			
		

			return new ResponseEntity<Object>(autor, HttpStatus.CREATED);
		}

		@PutMapping(value = "/editar", consumes = "application/json")
		public ResponseEntity<?> editar(@RequestBody Autor autor) throws ModelNotFoundException, Exception {
			service.editar(autor);
			
			return new ResponseEntity<Object>(autor,HttpStatus.OK);
		}

		// 204
		@DeleteMapping(value = "/eliminar/{idautor}")
		public ResponseEntity<?> eliminar(@PathVariable Integer idautor) throws ModelNotFoundException {
			System.out.print("antes de usar servixe");
			service.eliminar(idautor);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
	 
}
