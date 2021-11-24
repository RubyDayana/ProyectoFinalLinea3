package ucundi.edu.co.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ucundi.edu.co.entity.Libro;

public class AutorDto {
	
	private Integer id;
	
	
	private String cedula;	
	
	
	private String nombre;
	
	
	private String apellido;
	
	
	private String correo;

	
	private List<Libro> libro;


	public AutorDto(Integer id, String cedula, String nombre, String apellido, String correo, List<Libro> libro) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.libro = libro;
	}


	public AutorDto() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public List<Libro> getLibro() {
		return libro;
	}


	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
	
	
}
