package ucundi.edu.co.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ucundi.edu.co.entity.Autor;

public class LibroDto {

	
	private Integer id;
	
	
	private String nombre;	
	
	
	private String descripcion;	
	
	
	private Integer numeroPaginas;	

	
	private Autor autor;


	public LibroDto(Integer id, String nombre, String descripcion, Integer numeroPaginas, Autor autor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numeroPaginas = numeroPaginas;
		this.autor = autor;
	}


	public LibroDto() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}


	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
}
