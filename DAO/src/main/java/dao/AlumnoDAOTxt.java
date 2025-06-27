/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exceptions.EstadoException;
import exceptions.PersonaException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import static persona.Alumno.str2Alu;
import persona.Persona;

/**
 *
 * @author g.guzman
 */
public class AlumnoDAOTxt extends DAO<Alumno, Integer> {

    private RandomAccessFile raf;
    
    AlumnoDAOTxt(String fullpath) throws DAOException {
        try {
            raf = new RandomAccessFile(fullpath, "rws");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de E/S ("+ex.getMessage()+")");
        }
    }

    
    @Override
    public void create(Alumno alu) throws DAOException {
        if (exist(alu.getDni())){
            throw new DAOException("El alumno con DNI "+alu.getDni()+" ya existe");
        }
        
        try {
            raf.seek(raf.length()); // Se posicion al final del archivo
            raf.writeBytes(alu.toString()+System.lineSeparator());//escribe lo que le pasamos
            
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al intentar crear el alumno ("+ex.getMessage()+")");
        }
    }

    @Override
    public Alumno read(Integer dni) throws DAOException {
        try {
            /*
            raf.seek(0);
            String lineaAlu;
            Integer dniAlu;
            while ((lineaAlu = raf.readLine())!=null) {
                dniAlu = Integer.valueOf(lineaAlu.substirng(0,8));
                if (dniAlu.equals(dni) ) {
                    return AlumnoUtils.str2Alu(lineaAlu);
                }
            }
            } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de E/S ("+ex.getMessage()+")");
            } catch (PersonaException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al instanciar el Alumno ("+ex.getMessage()+")");
            }

            return null;
            }
            */
            
            raf.seek(0);
            String lineaAlu;
            String[] camposAlu;
            while ((lineaAlu = raf.readLine())!=null) {
                if (lineaAlu.trim().isEmpty()) continue;
                camposAlu = lineaAlu.split(Persona.DELIM);
                if (Integer.valueOf(camposAlu[0]).equals(dni) ) {
                    return Alumno.str2Alu(camposAlu);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de E/S ("+ex.getMessage()+")");
        } catch (PersonaException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al instanciar el Alumno ("+ex.getMessage()+")");
        }

        return null;
    }

    @Override    
    public void update(Alumno alu) throws DAOException {
        try {
            List<Alumno> todos = findAll(true); //  obtener todos los alumnos
            boolean encontrado = false;

            List<Alumno> actualizados = new ArrayList<>();
            for (Alumno a : todos) {

                if (String.valueOf(a.getDni()).trim().equals(String.valueOf(alu.getDni()).trim())) {
                    actualizados.add(alu);
                    encontrado = true;
                } else {
                    actualizados.add(a);
                }
            }
            if (!encontrado) {
                throw new DAOException("Alumno con DNI " + alu.getDni() + " no encontrado para modificar.");
            }

            raf.setLength(0);
            for (Alumno a : actualizados) {
                raf.writeBytes(a.toString() + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de E/S (" + ex.getMessage() + ")");
        }
    }

    @Override
    public void delete(Integer dni) throws DAOException {
        // Baja lógica
        Alumno alu = read(dni);
        try {
            alu.setEstado('B');
        } catch (EstadoException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
        update(alu);
    }

    @Override
    public boolean exist(Integer dni) throws DAOException {
        
        try {
            /*
            raf.seek(0);
            String lineaAlu;
            Integer dniAlu;
            while ((lineaAlu = raf.readLine())!=null) {
                dniAlu = Integer.valueOf(lineaAlu.substring(0,8));
                if (dniAlu.equals(dni) ) {
                    return true;
                }
            }
            */
            
            raf.seek(0);
            String lineaAlu;
            String[] camposAlu;
            while ((lineaAlu = raf.readLine())!=null) {
                if (lineaAlu.trim().isEmpty()) continue;
                camposAlu = lineaAlu.split(Persona.DELIM);
                if (Integer.valueOf(camposAlu[0]).equals(dni) ) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de E/S ("+ex.getMessage()+")");
        }
        return false;
    }

    @Override
    public List<Alumno> findAll(boolean includeDeleted) throws DAOException {
        List <Alumno> alumnos = new ArrayList <>();
        try {
            raf.seek(0);
            String lineaAlu;
                while ((lineaAlu = raf.readLine()) != null){
                    if (lineaAlu.trim().isEmpty()) continue;
                    Alumno alumno = str2Alu(lineaAlu.split("\\t"));
                    if (includeDeleted || alumno.getEstado() == 'A' || alumno.getEstado() == 'M') {
                    alumnos.add(alumno);
                    }
                }                
            } catch (IOException | PersonaException ex) {
                Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        return alumnos;
    }

    @Override
    public void close() throws DAOException {
        try {
            raf.close();
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("No se pudo cerrar el archivo.");
        }
        }
    
}
