/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.programaclase04cc;

/**
 *
 * @author fermin
 */
public class ProgramaClase04CC {

    public static void main(String[] args) {
        //Create
        DAOEmpleado dao=new DAOEmpleado();
//        Empleado emp= new Empleado();
//        emp.setClave(3);
//        emp.setNombre("Fermin");
//        emp.setDireccion("Avenida 2");
//        emp.setTelefono("1122334455");
//        dao.create(emp);
//        Empleado em=dao.create(emp);
//        System.out.println(em.getNombre());
//
//        //Delete
//        dao.delete(2);
//        
        //Update
//        Empleado p=new Empleado();
//        p.setNombre("Antonio");
//        p.setDireccion("Calle 2");
//        p.setTelefono("1122334455");
//        Empleado em=dao.update(1, p);
//        System.out.println(em.getNombre());
//        
        //FindALl
//        dao.findAll();
//        
        //Find by Id
        //dao.findById(2);
        Empleado em=dao.findById(3);
        System.out.println(em.getNombre());
    }
}
