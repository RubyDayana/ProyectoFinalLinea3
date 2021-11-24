package ucundi.edu.co.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmpleadoDtos {

	private int id;
	
    private String nombre;

   
    private String apellido;

  
    private String correo;

   
    private String cargo;

    
    private int edad;
    
  
    private double salario;

   
    private double bonus;





	public EmpleadoDtos(int id, String nombre, String apellido, String correo, String cargo, int edad, double salario,
			double bonus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
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


	public EmpleadoDtos() {
		
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
    
    
}


