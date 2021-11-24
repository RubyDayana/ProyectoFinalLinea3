package ucundi.edu.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ucundi.edu.co.entity.Autor;
import ucundi.edu.co.entity.Empleado;

@Repository
public interface IAutorRepo extends JpaRepository<Autor, Integer> {

	
	public Boolean existsByCedula(String cedula);	
	
	
	public Empleado findByCorreo(String correo);
	
	public Boolean existsByCorreo(String correo);
	
	
    @Query(value=" select count(*) from autor where nombre= :nombre", nativeQuery = true)
	public Integer existsByNombre(String nombre);
}
