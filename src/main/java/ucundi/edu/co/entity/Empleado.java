package ucundi.edu.co.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "empleado")
public class Empleado extends RepresentationModel<Empleado> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre", length = 20, nullable = false)
	@Size(min = 3, max = 20, message = "Este campo no puede estar vacio y no puede tener menos de 3 o mas de 20 letras")
	private String nombre;

	@Column(name = "apellido", length = 20, nullable = false)
	@Size(min = 3, max = 20, message = "Este campo no puede estar vacio y no puede tener menos de 3 o mas de 20 letras")
	private String apellido;

	@NotNull(message = "Cedula es obligatorio")
	@Size(min = 7, max = 12, message = "El Cedula debe estar entre 7 y 12 caracteres")
	@Column(name = "cedula", length = 12, nullable = false, unique = true)
	private String cedula;

	@Column(name = "correo", length = 20, nullable = false)
	@Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+ "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
			+ "(?:[A-Za-z0-9-]*[A-Za-z0-9])?", message = "El Correo que ingresó no es Válido")
	private String correo;

	@Column(name = "cargo", length = 20, nullable = false)
	@Size(min = 3, max = 30, message = "Este campo no puede tener menos de 3 o mas de 30 letras")
	private String cargo;

	@Column(name = "edad", nullable = false)
	@Min(value = 18, message = "La edad del usuario debe ser mayor de 18 años")
	@Max(value = 85, message = "La edad del usuario debe ser menor de 85 años")
	private int edad;

	@Column(name = "salario", nullable = false)
	@Min(value = 908526, message = "El salario no puede ser inferior a  1SMMLV")
	private double salario;

	@Column(name = "bonus", nullable = false)
	@Min(value = 0, message = "La bonificación no puede se menor a 0")
	@Max(value = 2000000, message = "La bonificación no puede se menor a 2 Millones ")
	private double bonus;

	public Empleado() {

	}

	public Empleado(int id,
			@Size(min = 3, max = 20, message = "Este campo no puede estar vacio y no puede tener menos de 3 o mas de 20 letras") String nombre,
			@Size(min = 3, max = 20, message = "Este campo no puede estar vacio y no puede tener menos de 3 o mas de 20 letras") String apellido,
			@NotNull(message = "Cedula es obligatorio") @Size(min = 7, max = 12, message = "El Cedula debe estar entre 7 y 12 caracteres") String cedula,
			@Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?", message = "El Correo que ingresó no es Válido") String correo,
			@Size(min = 3, max = 30, message = "Este campo no puede tener menos de 3 o mas de 30 letras") String cargo,
			@Min(value = 18, message = "La edad del usuario debe ser mayor de 18 años") @Max(value = 85, message = "La edad del usuario debe ser menor de 85 años") int edad,
			@Min(value = 908526, message = "El salario no puede ser inferior a  1SMMLV") double salario,
			@Min(value = 0, message = "La bonificación no puede se menor a 0") @Max(value = 2000000, message = "La bonificación no puede se menor a 2 Millones ") double bonus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.correo = correo;
		this.cargo = cargo;
		this.edad = edad;
		this.salario = salario;
		this.bonus = bonus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Empleado))
			return false;
		Empleado empelado = (Empleado) o;
		return Objects.equals(this.id, empelado.id) && Objects.equals(this.nombre, empelado.nombre)
				&& Objects.equals(this.cargo, empelado.cargo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.nombre, this.cargo);
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + this.id + ", nombre='" + this.nombre + '\'' + ", cargo='" + this.cargo + '\''
				+ '}';
	}

}
