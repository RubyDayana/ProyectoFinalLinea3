package ucundi.edu.co.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "rol")
public class Rol {

	@Id
	private Integer idRol;

	@Column(name = "nombre", length = 20)
	private String nombre;

	@Column(name = "descripcion", length = 30)
	private String descripcion;
	
	@OneToMany(mappedBy = "rol", orphanRemoval = true, fetch = FetchType.LAZY)    
    private List<Usuario> usuario;
	
	public Rol() {
		
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
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
	@JsonManagedReference
	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}
	
	
	
}
