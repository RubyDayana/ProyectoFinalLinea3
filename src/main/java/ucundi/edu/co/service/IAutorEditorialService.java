package ucundi.edu.co.service;

import ucundi.edu.co.entity.AutorEditorial;
import ucundi.edu.co.exception.ModelNotFoundException;

public interface IAutorEditorialService  extends ICrud<AutorEditorial, Integer> {

	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException;	
	
	public void asociarAutorEditoial();
}
