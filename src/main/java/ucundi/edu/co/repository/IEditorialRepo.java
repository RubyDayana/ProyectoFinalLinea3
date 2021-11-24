package ucundi.edu.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucundi.edu.co.entity.Editorial;
import ucundi.edu.co.entity.Empleado;



@Repository
public interface IEditorialRepo extends JpaRepository<Editorial, Integer> {

	public Boolean existsByCorreo(String correo);
	
	public Boolean existsById(int id);
	
	public Boolean existsByNit(String nit);
}
