/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exceptions.PersonaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import utils.DateUtils;

/**
 *
 * @author g.guzman
 */
public class AlumnoDAOSql extends DAO<Alumno, Integer> {

    private Connection conn;
    private PreparedStatement prepareStatementCreate;
    private PreparedStatement prepareStatementRead;
    private PreparedStatement prepareStatementAll;
    private PreparedStatement prepareStatementUpdate;
    private PreparedStatement prepareStatementDelete;
    private PreparedStatement prepareStatementExist;
            
    AlumnoDAOSql(String url, String user, String pwd) throws DAOException {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            
            String sqlCreate = "INSERT INTO alumnos\n" +
                                "(DNI,\n" +
                                "NOMBRE,\n" +
                                "APELLIDO,\n" +
                                "FEC_NAC,\n" +
                                "PROMEDIO,\n" +
                                "MAT_APR,\n" +
                                "FEC_ING,\n" +
                                "ESTADO)\n" +
                                "VALUES\n" +
                                "(?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?);";
            prepareStatementCreate = conn.prepareStatement(sqlCreate);
            
            String sqlRead = "SELECT * FROM alumnos WHERE DNI = ?";
            prepareStatementRead = conn.prepareStatement(sqlRead);

            String sqlAll = "SELECT * FROM alumnos";
            prepareStatementAll = conn.prepareStatement(sqlAll);
            
            // Agregado
            String sqlUpdate = "SELECT * FROM alumnos WHERE DNI = ?";
            prepareStatementUpdate = conn.prepareStatement(sqlUpdate);
            
            String sqlDelete = "SELECT * FROM alumnos";
            prepareStatementDelete = conn.prepareStatement(sqlDelete);
            
            String sqlExist = "SELECT * FROM alumnos WHERE DNI = ?";
            prepareStatementExist = conn.prepareStatement(sqlExist);
           
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de SQL ("+ex.getMessage()+")");
        }
    }

    
    @Override
    public void create(Alumno alu) throws DAOException {
        try {
            int index = 0;
            prepareStatementCreate.setInt(++index, alu.getDni());
            prepareStatementCreate.setString(++index, alu.getNombre());
            prepareStatementCreate.setString(++index, alu.getApellido());
            prepareStatementCreate.setDate(++index, DateUtils.localeDate2SqlDate(alu.getFecNac()));
            
            // Agregado
            prepareStatementCreate.setDouble(++index, alu.getPromedio());
            prepareStatementCreate.setInt(++index, alu.getMatApr());
            prepareStatementCreate.setDate(++index, DateUtils.localeDate2SqlDate(alu.getFecIng()));
            prepareStatementCreate.setInt(++index, alu.getEstado());
            
            prepareStatementCreate.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de SQL ("+ex.getMessage()+")");
        }
    }

    @Override
    public Alumno read(Integer dni) throws DAOException {
        try {
            prepareStatementRead.setInt(1, dni);
            ResultSet rs = prepareStatementRead.executeQuery();
            if (rs.next()) {
                
                // Agregado, problema con char y date
                /*
                Alumno alumno= new Alumno();
                alumno.setDni(rs.getInt("DNI"));
                alumno.setNombre(rs.getString("NOMBRE"));
                alumno.setApellido(rs.getString("APELLIDO"));
                alumno.setFecNac(DateUtils.localeDate2SqlDate(rs.getDateChooser("FEC_NAC"));
                alumno.setPromedio(rs.getDouble("PROMEDIO"));
                alumno.setMatApr(rs.getInt("MAT_APR"));
                alumno.setFecIng(DateUtils.localeDate2SqlDate(rs.getDateChooser("FEC_ING"));
                alumno.setEstado(rs.getChar("ESTADO"));
                */
               
                
                return buildAlumnoFromDB(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de SQL ("+ex.getMessage()+")");
        } catch (PersonaException ex) {
            Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al setear datos del alumno ("+ex.getMessage()+")");
        }
        
        return null;
    }

    private Alumno buildAlumnoFromDB(ResultSet rs) throws PersonaException, SQLException {
        Alumno alu = new Alumno();
        alu.setDni(rs.getInt("DNI"));
        alu.setNombre(rs.getString("NOMBRE"));
        alu.setApellido(rs.getString("APELLIDO"));
        alu.setFecNac(rs.getDate("FEC_NAC").toLocalDate());
        return alu;
    }

    @Override
    public void update(Alumno alumno) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer dni) throws DAOException {
        try {
            prepareStatementDelete.setInt(1, dni);
             int affectedRows=prepareStatementDelete.executeUpdate();
        if (affectedRows==0) {
            throw new DAOException("No se pudo encontrar el alumno con DNI: "+dni);
        }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean exist(Integer dni) throws DAOException {
        try {
            prepareStatementExist.setInt(1, dni);
             ResultSet rs=prepareStatementExist.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            return count >0;
        }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al verificar la existencia del alumno: "+dni);
        }
        return false;
    }

    @Override
    public List<Alumno> findAll(boolean includeDeleted) throws DAOException {
        List<Alumno> alumnos = new ArrayList<>();
        try {
            ResultSet rs = prepareStatementAll.executeQuery();
            while (rs.next()) {
                alumnos.add(buildAlumnoFromDB(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error de SQL ("+ex.getMessage()+")");
        } catch (PersonaException ex) {
            Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al setear datos del alumno ("+ex.getMessage()+")");
        }
        
        return alumnos;
        
    }

    @Override
    public void close() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
