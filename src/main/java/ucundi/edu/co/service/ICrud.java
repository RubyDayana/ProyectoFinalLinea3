package ucundi.edu.co.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ucundi.edu.co.exception.ArgumentRequiredException;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;

public interface ICrud <T, ID> {
	
	public Page<T> retornarPaginado(int page, int size);

	public Page<T> retornarPaginado(Pageable page);

	public T retonarPorId(ID idUser) throws ModelNotFoundException;

	public void guardar(T user) throws ConflictException, ModelNotFoundException;

	public void editar(T user) throws ArgumentRequiredException, ModelNotFoundException, ConflictException;

	public void eliminar(int idUser) throws ModelNotFoundException;

}
