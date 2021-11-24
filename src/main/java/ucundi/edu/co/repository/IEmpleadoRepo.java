package ucundi.edu.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucundi.edu.co.entity.Empleado;

@Repository
public interface IEmpleadoRepo extends JpaRepository<Empleado, Integer> {

	public Empleado findByCedula(String cedula);

	public Empleado findByCorreo(String correo);

	public Boolean existsByCedula(String cedula);

	public Boolean existsByCorreo(String correo);
	
	//JPQL
	
	//SQL
	
	//correo existe sin tener en cuenta el propio
	
	// findBy todas las opciones
}
