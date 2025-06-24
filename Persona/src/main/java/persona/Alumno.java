/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persona;

import exceptions.PersonaException;
import java.time.LocalDate;

/**
 *
 * @author g.guzman
 */
public class Alumno extends Persona {

    private Double promedio;
    private Integer MatApr;
    private LocalDate fecIng;
    private char estado; // A - M - B

    public Alumno() {
        super();
        promedio = 0.0;
    }

    public Alumno(Double promedio, Integer dni) throws PersonaException {
        super(dni);
        this.promedio = promedio;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) throws PersonaException{
        if (promedio==null || promedio < 0 || promedio > 10.0) {
            throw new PersonaException("El promedio "+promedio+" es inválido, inserte valores entre 0 y 10");
        }
        this.promedio = promedio;
        
    }

    public Integer getMatApr() {
        return MatApr;
    }

    public void setMatApr(Integer MatAprob) throws PersonaException {
        if (MatAprob==null || MatAprob < 0 || MatAprob > 30) {
            throw new PersonaException("La cantidad de materias aprobadas "+MatAprob+" es inválido para la carrera");
        }
        this.MatApr = MatAprob;
    }

    public LocalDate getFecIng() {
        return fecIng;
    }

    public void setFecIng(LocalDate fecIng) {
        this.fecIng = fecIng;
    }

    
    
    @Override
    public String toString() {
        return super.toString() + String.format("", null, null); 
    }
    
    public static Alumno str2Alu(String[] camposAlu) throws PersonaException {

        int index = 0;
        Alumno alumno = new Alumno();
        alumno.setDni(Integer.valueOf(camposAlu[index++]));
        alumno.setNombre(camposAlu[index++]);
        
        alumno.setApellido(camposAlu[index++]);
        
        String[] fecNacStr = camposAlu[index++].split("/"); // por ej: "23/05/2025"
        int year = Integer.valueOf(fecNacStr[2]);
        int month = Integer.valueOf(fecNacStr[1]);
        int day = Integer.valueOf(fecNacStr[0]);
        LocalDate fecNac = LocalDate.of(year,  month, day);
        alumno.setFecNac(fecNac);
        
        return alumno;
    }

}
