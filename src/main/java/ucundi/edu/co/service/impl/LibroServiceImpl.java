package ucundi.edu.co.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ucundi.edu.co.dto.LibroDto;
import ucundi.edu.co.entity.Libro;
import ucundi.edu.co.exception.ArgumentRequiredException;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.repository.ILibroRepo;
import ucundi.edu.co.service.ILibroService;

@Service
public class LibroServiceImpl implements ILibroService {

	@Autowired
	private ILibroRepo repo;

	@Override
	public Page<Libro> retornarPaginado(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Libro> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Libro retonarPorId(Integer idLibro) throws ModelNotFoundException {
		return repo.findById(idLibro).orElseThrow(() -> new ModelNotFoundException("Libro no encontrado"));

	}

	@Override
	public void guardar(Libro libro) throws ConflictException {
		System.out.print("antes de validad el nombre");

		if (repo.existsByNombre(libro.getNombre())) {
			throw new ConflictException("Libro ya existe");
		} else {
			repo.save(libro);
		}

	}

	@Override
	public void editar(Libro libro) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {

		if (validarExistenciaPorId(libro.getId())) {

			Libro libroAux = this.repo.findById(libro.getId()).get();

			libro.setNombre(libroAux.getNombre());

		} else {
			throw new ModelNotFoundException("Libro no encontrado");
		}

	}

	@Override
	public void eliminar(int idLibro) throws ModelNotFoundException {

		if (validarExistenciaPorId(idLibro))
			this.repo.deleteById(idLibro);
		else
			throw new ModelNotFoundException("Libro no encontrado");
	}

	private Boolean validarExistenciaPorId(int idLibro) {

		return repo.existsById(idLibro);
	}

}
