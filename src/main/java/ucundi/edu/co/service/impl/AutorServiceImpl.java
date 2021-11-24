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

import ucundi.edu.co.dto.AutorDto;
import ucundi.edu.co.entity.Autor;
import ucundi.edu.co.exception.ArgumentRequiredException;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.repository.IAutorRepo;
import ucundi.edu.co.service.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService {

	@Autowired
	private IAutorRepo repo;

	@Override
	public Page<Autor> retornarPaginado(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Autor> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}

	public Autor retornarPorIdDetalle(Integer idAutor, boolean detalle) throws ModelNotFoundException {
		Autor autor =repo.findById(idAutor).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));
		if(!detalle) {
			autor.setLibro(new ArrayList<>());
		}
		return autor;
	}

	@Override
	public Autor retonarPorId(Integer idAutor) throws ModelNotFoundException {
		return repo.findById(idAutor).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));

	}

	@Override
	public void guardar(Autor autor) throws ConflictException {
		if (repo.existsByCedula(autor.getCedula())) {
			throw new ConflictException("Cedula ya existe");
		}
		if (repo.existsByCorreo(autor.getCorreo()))
			throw new ConflictException("Correo ya existe");
		if(repo.existsByNombre(autor.getNombre())>0) {
			throw new ConflictException("Nombre se repite");
		}
		this.repo.save(autor);

	}

	@Override
	public void editar(Autor autor) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {

		

		if (validarExistenciaPorId(autor.getId())) {
			System.out.print("Entro if existe id");
			// repo.save(autor);

			Autor autorAux = this.repo.findById(autor.getId()).get();

			autor.setCedula(autorAux.getCedula());

			if (autor.getCorreo().equals(autorAux.getCorreo())) {

				System.out.print("Entro if correo");
				this.repo.save(autor);
			} else {
				if (!repo.existsByCorreo(autor.getCorreo())) {
					this.repo.save(autor);
				} else {
					throw new ConflictException("Correo ya existe");
				}
			}

		} else {
			throw new ModelNotFoundException("Autor no encontrado");
		}

	}

	@Override
	public void eliminar(int idAutor) throws ModelNotFoundException {

		if (validarExistenciaPorId(idAutor)) {
			System.out.print("Valido y id si existe");
			this.repo.deleteById(idAutor);
		} else {
			throw new ModelNotFoundException("Autor no encontrado");
		}
	}

	private Boolean validarExistenciaPorId(int idAutor) {

		return repo.existsById(idAutor);
	}

}
