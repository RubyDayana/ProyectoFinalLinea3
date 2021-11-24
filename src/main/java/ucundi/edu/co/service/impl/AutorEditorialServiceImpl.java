package ucundi.edu.co.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucundi.edu.co.entity.AutorEditorial;
import ucundi.edu.co.exception.ArgumentRequiredException;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.repository.IAutorEditorialRepo;
import ucundi.edu.co.repository.IAutorRepo;
import ucundi.edu.co.repository.IEditorialRepo;
import ucundi.edu.co.service.IAutorEditorialService;


@Service
public class AutorEditorialServiceImpl implements IAutorEditorialService{

	@Autowired
	private IAutorRepo repoAutor;
	
	@Autowired
	private IEditorialRepo repoEditorial;
	
	@Autowired
	private IAutorEditorialRepo repo;
	
	@Override
	public Page<AutorEditorial> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AutorEditorial> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutorEditorial retonarPorId(Integer idObj) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(AutorEditorial obj) throws ConflictException, ModelNotFoundException {
		System.out.print("Guardar AutorEditorial");
		System.out.print(obj.getAutor().getId().toString());	
		if(!repoAutor.existsById(obj.getAutor().getId())) {
			throw new ModelNotFoundException("Autor no existe");
		}
		
		if(!repoEditorial.existsById(obj.getEditorial().getId())) {
			throw new ModelNotFoundException("Editorial no existe");
		}
		
		this.repo.guardarNativo(obj.getAutor().getId(), obj.getEditorial().getId(), obj.getFecha());
	}

	@Override
	public void editar(AutorEditorial obj) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		//Generalmente no se utiliza el editar en una table intermedia 
	}

	@Override
	public void eliminar(int obj) throws ModelNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		//Validaciones pertinentes
		this.repo.eliminarNativa(idAutor, idEditorial);
	}

	@Transactional
	@Override
	public void asociarAutorEditoial() {
		this.repo.guardarNativo(1, 1, LocalDate.now());
		this.repo.guardarNativo(2, 1, LocalDate.now());
		this.repo.guardarNativo(3, 1, LocalDate.now());
		this.repo.guardarNativo(6, 1, LocalDate.now());
	}

}
