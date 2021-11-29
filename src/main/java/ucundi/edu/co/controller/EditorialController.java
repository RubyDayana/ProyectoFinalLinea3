package ucundi.edu.co.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucundi.edu.co.entity.Autor;
import ucundi.edu.co.entity.AutorEditorial;
import ucundi.edu.co.entity.Editorial;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.service.IAutorEditorialService;
import ucundi.edu.co.service.IEditorialService;

@PreAuthorize("hasAuthority('Editorial')")
@RestController
@RequestMapping("/editorial")
public class EditorialController {

	@Autowired
	private IAutorEditorialService serviceEA;

	@Autowired
	private IEditorialService service;

	@GetMapping(value = "/obtenerPaginado/{page}/{size}", produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Editorial> listaEditorial = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Editorial>>(listaEditorial, HttpStatus.OK);
	}

	@PostMapping(value = "/asociarEditorial", consumes = "application/json")
	public ResponseEntity<?> asociarEditorail(@Valid @RequestBody AutorEditorial autorEditorial)
			throws ConflictException, ModelNotFoundException {
		serviceEA.guardar(autorEditorial);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	// 204
	@DeleteMapping(value = "/eliminarAsociacion/{idAutor}/{idEditorial}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idAutor, @PathVariable Integer idEditorial)
			throws ModelNotFoundException {
		serviceEA.eliminarNativo(idAutor, idEditorial);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Editorial editorial) throws ConflictException, ModelNotFoundException {

		service.guardar(editorial);

		return new ResponseEntity<Object>(editorial, HttpStatus.CREATED);
	}

	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@RequestBody Editorial editorial) throws ModelNotFoundException, Exception {
		service.editar(editorial);

		return new ResponseEntity<Object>(editorial, HttpStatus.OK);
	}

	// 204
	@DeleteMapping(value = "/eliminar/{idEditorial}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idEditorial) throws ModelNotFoundException {
		System.out.print("antes de usar servixe");
		service.eliminar(idEditorial);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
