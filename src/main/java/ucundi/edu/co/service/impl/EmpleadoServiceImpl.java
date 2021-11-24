/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucundi.edu.co.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import ucundi.edu.co.entity.Empleado;
import ucundi.edu.co.exception.ArgumentRequiredException;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.repository.IEmpleadoRepo;
import ucundi.edu.co.service.IEmpleadoService;

/**
 *
 * @author Ruby Dayana, Andres Gómez
 */
@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
	@Autowired
	private IEmpleadoRepo repo;

	@Override
	public Page<Empleado> retornarPaginado(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));

	}

	@Override
	public Page<Empleado> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Empleado retonarPorId(Integer idEmpleado) throws ModelNotFoundException {

		return repo.findById(idEmpleado).orElseThrow(() -> new ModelNotFoundException("Empleado no encontrado"));
	}

	@Override
	public void guardar(Empleado empleado) throws ConflictException {
		if (repo.existsByCedula(empleado.getCedula())) {
			throw new ConflictException("Cedula ya existe");
		}
		if (repo.existsByCorreo(empleado.getCorreo())) {
			throw new ConflictException("Correo ya existe");
		}

		this.repo.save(empleado);

	}

	@Override
	public void editar(Empleado empleado) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {

		if (validarExistenciaPorId(empleado.getId())) {

			Empleado empleadoAux = this.repo.findById(empleado.getId()).get();
			
			empleado.setCedula(empleadoAux.getCedula());

			if (empleado.getCorreo().equals(empleadoAux.getCorreo()))
				this.repo.save(empleado);
			else {
				if (!repo.existsByCorreo(empleado.getCorreo())) {
					this.repo.save(empleado);
				} else {
					throw new ConflictException("Correo ya existe");
				}
			}

		} else {
			throw new ModelNotFoundException("Empelado no encontrado");
		}

	}

	@Override
	public void eliminar(int idEmpleado) throws ModelNotFoundException {

		if (validarExistenciaPorId(idEmpleado))
			this.repo.deleteById(idEmpleado);
		else
			throw new ModelNotFoundException("Empleado no encontrado");
	}

	private Boolean validarExistenciaPorId(int idEmpleado) {

		return repo.existsById(idEmpleado);
	}

}
