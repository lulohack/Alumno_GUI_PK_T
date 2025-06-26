/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testpersona;

import exceptions.ApellidoNullException;
import exceptions.ApellidoVacioException;
import exceptions.EstadoException;
import exceptions.MateriasAprobadasException;
import exceptions.NombreNullException;
import exceptions.NombreVacioException;
import exceptions.PersonaException;
import exceptions.PromedioException;
import java.rmi.AccessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.Persona;

/**
 *
 * @author g.guzman
 */
public class TestPersona {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Ejecuta OK");
        Persona unaPersona = new Persona();
        try {
            unaPersona.setDni(656565);
        } catch (PersonaException e) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, e);
            return;
        }
        finally {
            //System.out.println("finally ejecutado");
        }

        // System.out.println("Persona: "+unaPersona);
        //System.out.println("Persona con DNI: " + unaPersona.getDni());

        //////////////////////////////
        /*
        Persona otraPersona;
        try {
            otraPersona = new Persona(555666777);
        } catch (PersonaException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        System.out.println("Otra Persona con DNI: " + otraPersona.getDni());
        */
        //////////////////////////////

        //////////////////////////////
/*        Persona fullPersona;
        try {
            fullPersona = new Persona(555666777);
        } catch (PersonaException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        try {
            fullPersona.setNombre(null);
        } catch (PersonaException exception) {
            if (exception instanceof NombreNullException) {
                Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, exception);
            }
            else if (exception instanceof NombreVacioException) {
                Logger.getLogger(TestPersona.class.getName()).log(Level.WARNING, null, exception);
            }
        }
*/
        // fullPersona.setApellido("Perez   ");
        /*
        System.out.println("Full Persona con DNI: "+fullPersona.getDni() + " con nombre = "+
        fullPersona.getNombre() + " "+fullPersona.getApellido());
         */
        /*
        System.out.println("Full Persona con DNI: " + fullPersona.getDni() + " con nombre = "
                + fullPersona.getFullName());
        //////////////////////////////

        Persona fullPersona2 = new Persona(555666777, "Carlos   ", "    Gomez");
        System.out.println("Full Persona2 con DNI: " + fullPersona2.getDni() + " con nombre = "
                + fullPersona2.getFullName());
        */
        ////////////////////////
        //Persona personaConNombreNull = new Persona(95959595);
        // Persona personaConNombreNull = new Persona();
        /*
        try {
            //personaConNombreNull.setNombre(null);
            //personaConNombreNull.setNombre("");
            personaConNombreNull.setNombre("María   ");
            ////////////////////////
       } catch (NombreNullException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (NombreVacioException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.WARNING, null, ex);
            return;
        }
        catch (PersonaException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.WARNING, null, ex);
        }
        */
        /*
        try {
            ////////////////////////
            Persona aluDni = new Alumno(7.25, -1551515);
        } catch (PersonaException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        */
        ////////////////////////
        
        //Persona otraPersona;
        /*
        otraPersona.setApellido("alvarez");
        System.out.println("Persona con apellido: " + otraPersona.getApellido());*/
/*        
        Persona otraPersona = new Persona();
        try {
            otraPersona.setApellido("  Alvarez   ");
        } catch (ApellidoNullException | ApellidoVacioException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(otraPersona.getApellido());
*/
        /*
         Persona otraPersona = new Persona();
        try {
            otraPersona.setApellido("  Alvarez   ");
        } catch (ApellidoNullException | ApellidoVacioException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(otraPersona.getApellido());*/
        /*
        Alumno alumno = new Alumno();

        try {
            alumno.setPromedio(11.0);
        } catch (PromedioException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(alumno.getPromedio());
        */
        
        /*
        Alumno alumno = new Alumno();

        try {
            alumno.setMatApr(35);
        } catch (MateriasAprobadasException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(alumno.getMatApr());
        */
        /*
         Alumno alumno = new Alumno();

        try {
            alumno.setEstado('A');
        } catch (EstadoException ex) {
            Logger.getLogger(TestPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(alumno.getEstado());
        */

        
        System.out.println("FIN OK");
    }
}
