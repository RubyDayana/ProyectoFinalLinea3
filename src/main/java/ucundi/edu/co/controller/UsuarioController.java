package ucundi.edu.co.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucundi.edu.co.entity.Usuario;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.service.IUsuarioService;

@RequestMapping("/usuario")
@RestController
@Validated

public class UsuarioController {

	@Autowired
	private IUsuarioService service;

	@PostMapping(value = "/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario) throws ConflictException, ModelNotFoundException {

		service.guardar(usuario);

		return new ResponseEntity<Object>(usuario, HttpStatus.CREATED);
	}
}
