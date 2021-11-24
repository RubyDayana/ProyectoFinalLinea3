package ucundi.edu.co.service;

import ucundi.edu.co.dto.AutorDto;
import ucundi.edu.co.entity.Autor;
import ucundi.edu.co.exception.ModelNotFoundException;

public interface IAutorService extends ICrud<Autor, Integer>{

	public Autor retornarPorIdDetalle(Integer idAutor, boolean detalle) throws ModelNotFoundException; 
}
