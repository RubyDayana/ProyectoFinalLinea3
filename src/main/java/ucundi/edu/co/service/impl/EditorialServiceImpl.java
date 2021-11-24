package ucundi.edu.co.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ucundi.edu.co.entity.Editorial;
import ucundi.edu.co.exception.ArgumentRequiredException;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.repository.IEditorialRepo;
import ucundi.edu.co.service.IEditorialService;


@Service
public class EditorialServiceImpl implements IEditorialService {

	@Autowired
	private IEditorialRepo repo;
	
	@Override
	public Page<Editorial> retornarPaginado(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Editorial> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}
	
	

	@Override
	public Editorial retonarPorId(Integer idEditorial) throws ModelNotFoundException {
		return repo.findById(idEditorial).orElseThrow(() -> new ModelNotFoundException("Editorial no encontrado"));
		
	}
	
	

	@Override
	public void guardar(Editorial editorial) throws ConflictException {
		System.out.print("antes de validad el nombre");
		if(repo.existsByCorreo(editorial.getCorreo())) {
			throw new ConflictException("Correo ya existe");
		}
		
		if(repo.existsByNit(editorial.getNit())) {
			throw new ConflictException("Nit ya existe");
		}
		
		
		
		repo.save(editorial);		
		
	}

	@Override
	public void editar(Editorial editorial) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		
		if(repo.existsByCorreo(editorial.getCorreo())) {
			throw new ConflictException("Correo ya existe");
		}
		
		if(repo.existsByNit(editorial.getNit())) {
			throw new ConflictException("Nit ya existe");
		}
		
		if (validarExistenciaPorId(editorial.getId())) {

			Editorial editorialAux = this.repo.findById(editorial.getId()).get();
			
			editorial.setNombre(editorialAux.getNombre());			

		} else {
			throw new ModelNotFoundException("Editorial no encontrado");
		}
		
		
	}

	@Override
	public void eliminar(int idEditorial) throws ModelNotFoundException {

		if (validarExistenciaPorId(idEditorial))
			this.repo.deleteById(idEditorial);
		else
			throw new ModelNotFoundException("Editorial no encontrado");
	}

	private Boolean validarExistenciaPorId(int idEditorial) {

		return repo.existsById(idEditorial);
	}
	

}
