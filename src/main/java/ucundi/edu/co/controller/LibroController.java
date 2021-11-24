package ucundi.edu.co.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucundi.edu.co.entity.Libro;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.service.ILibroService;

@PreAuthorize("hasAuthority('Autor')")
@RequestMapping("/libro")
@RestController
@Validated

public class LibroController {

	@Autowired
	private ILibroService service;
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Libro> listaLibro = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Libro>>(listaLibro, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Libro> listaLibro = service.retornarPaginado(page);
		return new ResponseEntity<Page<Libro>>(listaLibro, HttpStatus.OK);	
	}

	

	@GetMapping(value = "/obtenerById/{id}", produces = "application/json")
	public ResponseEntity<?> obtenerById(@PathVariable int id) throws ModelNotFoundException, Exception {
		Libro Libro = service.retonarPorId(id);

		return new ResponseEntity<>(Libro, HttpStatus.OK);

	}

	@PostMapping(value = "/guardar")
	public ResponseEntity<Libro> guardar(@Valid @RequestBody Libro Libro) throws ModelNotFoundException, Exception {
		System.out.print("antes de guardar libro");
		service.guardar(Libro);
		
	

		return new ResponseEntity<Libro>(Libro, HttpStatus.CREATED);
	}

	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@RequestBody Libro Libro) throws ModelNotFoundException, Exception {
		service.editar(Libro);
		
		return new ResponseEntity<Object>(Libro,HttpStatus.OK);
	}

	//204
		@DeleteMapping(value = "/eliminar/{id}")
		public ResponseEntity<?> eliminar(@PathVariable Integer id)  throws ModelNotFoundException{
			service.eliminar(id);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
 
}
