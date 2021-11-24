/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucundi.edu.co.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 *
 * @author Ruby Dayana, Andres Gómez
 */
public class EmpleadoDto {

    @Size(min = 3, max = 20, message = "Este campo no puede estar vacio y no puede tener menos de 3 o mas de 20 letras")
    private String nombre;

    @Size(min = 3, max = 20, message = "Este campo no puede estar vacio y no puede tener menos de 3 o mas de 20 letras")
    private String apellido;

    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
            + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?",
            message = "El Correo que ingresó no es Válido")
    private String correo;

    @Size(min = 3, max = 30, 
            message = "Este campo no puede tener menos de 3 o mas de 30 letras")
    private String cargo;

    @Min(value = 18, message = "La edad del usuario debe ser mayor de 18 años")
    @Max(value = 85, message = "La edad del usuario debe ser menor de 85 años")
    private int edad;
    
    @Min(value = 908526, message = "El salario no puede ser inferior a  1SMMLV")
    private double salario;

    @Min(value = 0, message = "La bonificación no puede se menor a 0")
    @Max(value = 2000000, message = "La bonificación no puede se menor a 2 Millones ")
    private double bonus;

    public EmpleadoDto(String nombre, String apellido, String correo, String cargo, int edad, double salario, double bonus) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cargo = cargo;
        this.edad = edad;
        this.salario = salario;
        this.bonus = bonus;
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
