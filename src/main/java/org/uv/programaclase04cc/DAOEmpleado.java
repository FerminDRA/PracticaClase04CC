package org.uv.programaclase04cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fermin
 */
public class DAOEmpleado implements IDAOGeneral<Empleado, Integer>{

    @Override
    public Empleado create(Empleado p) {
        ConexionDB cx= ConexionDB.getInstance();
        TransactionDB tbd=new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try(PreparedStatement psm=con.prepareStatement("insert into empleado(clave, nombre, direccion, telefono)"
                            + "values (?,?,?,?)")) {
                    psm.setInt(1, p.getClave());
                    psm.setString(2, p.getNombre());
                    psm.setString(3, p.getDireccion());
                    psm.setString(4, p.getTelefono());
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean resp=cx.execute(tbd);
        
        if(resp){
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Se ha creado...");
            return p;
        }
        else{
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "No se ha podido guardar");
            return null;
        }
    }

    @Override
    public boolean delete(Integer id) {
        ConexionDB cx= ConexionDB.getInstance();
        TransactionDB tbd=new TransactionDB<Integer>(id) {
            @Override
            public boolean execute(Connection con) {
                try (PreparedStatement psm= con.prepareStatement("delete from empleado where clave=?")){                    
                    psm.setInt(1, id);
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        
        boolean resp=cx.execute(tbd);
        if(resp){
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE,"Se ha eliminado");
            return true;
        }
        else{
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE,"Error");
            return false;
        }
    }

    @Override
    public Empleado update(Integer id, Empleado p) {
        ConexionDB cx= ConexionDB.getInstance();
        p.setClave(id);
        TransactionDB tbd=new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try(PreparedStatement psm= con.prepareStatement("update empleado set nombre=?, direccion=?, telefono=? where clave=?")) {
                    psm.setString(1, p.getNombre());
                    psm.setString(2, p.getDireccion());
                    psm.setString(3, p.getTelefono());
                    psm.setInt(4, p.getClave());
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean resp=cx.execute(tbd);
        
        if(resp){
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Se ha actualizado...");
            return p;
        }
        else{
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "No se ha podido guardar");
            return null;
        }
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> empleados= new ArrayList<>();
        
        
        ConexionDB cx= ConexionDB.getInstance();
        TransactionDB tbd=new TransactionDB<List<Empleado>>(empleados) {
            @Override
            public boolean execute(Connection con) {
                try(PreparedStatement psm= con.prepareStatement("select * from empleado")) {
                    List<Empleado> empleados= new ArrayList<>();
                    ResultSet rs=null;
                    Empleado emp;
                    rs=psm.executeQuery();
                    
                    
                    if(!rs.next()){
                        return false;
                    }
                    else{
                        while(rs.next()){
                        emp=new Empleado();
                        emp.setClave(rs.getInt(1));
                        emp.setNombre(rs.getString(2));
                        emp.setDireccion(rs.getString(3));
                        emp.setTelefono(rs.getString(4));
                        empleados.add(emp);
                    }
                        return true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean resp=cx.execute(tbd);
        if (resp) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Lista empleados");
            return empleados;
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Lista vacia");
            return null;
        }
    }

    @Override
    public Empleado findById(Integer id) {
        ConexionDB cx= ConexionDB.getInstance();
        Empleado p=new Empleado();
        TransactionDB tbd=new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try(PreparedStatement psm= con.prepareStatement("select * from empleado where clave=?")) {
                    Empleado emp=new Empleado();
                    //
                    ResultSet rs=null;
                    psm.setInt(1, id);
                    rs=psm.executeQuery();
                    
                    
                    if(!rs.next()){
                        return false;
                    }
                    else{
                        while(rs.next()){
                        emp.setClave(rs.getInt(1));
                        emp.setNombre(rs.getString(2));
                        emp.setDireccion(rs.getString(3));
                        emp.setTelefono(rs.getString(4));
                    }
                        return true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        
        boolean resp=cx.execute(tbd);
        if(resp){
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE,"Empleado encontrado");
            return p;
        }
        else{
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE,"Empleado no encontrado");
            return null;
        }
    }
    
}
