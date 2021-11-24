/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucundi.edu.co.controller;

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

import ucundi.edu.co.entity.Empleado;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.service.IEmpleadoService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


/**
 *
 * @author Ruby Dayana, Andres Gómez
 */
@PreAuthorize("hasAuthority('empleado')")
@RequestMapping("/empleado")
@RestController
@Validated
public class EmpleadoController {

	@Autowired
	private IEmpleadoService service;
	
	@GetMapping(value = "/gets/{i}/{nombre}/{correo}", produces = "application/json")
	public ResponseEntity<?> obtenerdos(
			@PathVariable("i") @Min(value = 1, message = "El código debe ser mayor o igual a 1") @Max(value = 100, message = "El código debe ser menor o igual a 100") int i,
			@PathVariable("nombre") @Size(min = 3, max = 30, message = "Este campo no puede tener menos de 3 o mas de 10 letras") String nombre,
			@PathVariable("correo") @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
					+ "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
					+ "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
					+ "(?:[A-Za-z0-9-]*[A-Za-z0-9])?", message = "El Correo que ingresó no es Válido") String correo)
			throws ModelNotFoundException, Exception ,MethodArgumentNotValidException{
		//EmpleadoDto empleado = service.obtenerDos(i);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Empleado> listaEmpleado = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Empleado>>(listaEmpleado, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Empleado> listaEmpleado = service.retornarPaginado(page);
		return new ResponseEntity<Page<Empleado>>(listaEmpleado, HttpStatus.OK);	
	}

	

	@GetMapping(value = "/obtenerById/{id}", produces = "application/json")
	public ResponseEntity<?> obtenerById(@PathVariable int id) throws ModelNotFoundException, Exception {
		Empleado empleado = service.retonarPorId(id);

		return new ResponseEntity<>(empleado, HttpStatus.OK);

	}

	@PostMapping(value = "/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Empleado empleado) throws ModelNotFoundException, Exception {
		
		service.guardar(empleado);
		
		empleado.add(linkTo(methodOn(EmpleadoController.class).editar(empleado)).withSelfRel());

		//empleado.add(linkTo(methodOn(EmpleadoController.class).retonarPaginado(empleado)).withSelfRel());

		empleado.add(linkTo(methodOn(EmpleadoController.class).obtenerById(empleado.getId())).withSelfRel());

		return new ResponseEntity<Object>(empleado, HttpStatus.CREATED);
	}

	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@RequestBody Empleado empleado) throws ModelNotFoundException, Exception {
		service.editar(empleado);
		//empleado.add(linkTo(methodOn(EmpleadoController.class).obtener(empleado)).withSelfRel());

		empleado.add(linkTo(methodOn(EmpleadoController.class).obtenerById(empleado.getId())).withSelfRel());
		return new ResponseEntity<Object>(empleado,HttpStatus.OK);
	}

	// 204
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int idEmpleado) throws ModelNotFoundException {
		service.eliminar(idEmpleado);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
