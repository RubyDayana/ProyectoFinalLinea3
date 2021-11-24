package ucundi.edu.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucundi.edu.co.entity.Libro;

@Repository
public interface ILibroRepo extends JpaRepository<Libro, Integer> {

	public Boolean existsByNombre(String nombre);

}
