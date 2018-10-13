/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoBitacora;
import sys.model.BitacoraRecibos;
import sys.model.MaestroMaterias;
import sys.util.HibernateUtil;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class BitacoraImp implements daoBitacora {
    
    HttpServletRequest request ;
        HttpSession sessionUsuario;
        int user ;
        int empleado;


    public BitacoraImp(int i) {
         request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
         sessionUsuario = request.getSession();
         user = (int) sessionUsuario.getAttribute("idSesion");
         empleado = (int) sessionUsuario.getAttribute("idEmpleado");
    }

    public BitacoraImp(){
        
    }
    
    @Override
    public boolean insertarTratamiento(BitacoraRecibos bitacora) {
        boolean inserto = false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bitacora);
            session.getTransaction().commit();
            inserto = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return inserto;
    }

    @Override
    public List<BitacoraRecibos> mostratTratamientosPorAlumno(MaestroMaterias mmaterias) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessionUsuario = request.getSession();
        int matricula=(int)sessionUsuario.getAttribute("idSesion");
        List<BitacoraRecibos> listBita = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from BitacoraRecibos as m inner join fetch m.alumnos left join fetch m.maestroMaterias "
                + "left join fetch m.menuTratamientos where m.maestroMaterias.clave = '" + mmaterias.getClave() + "' and m.alumnos.matricula=" + matricula;
        try {
            listBita = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listBita;
    }

    @Override
    public List<BitacoraRecibos> mostrarTratamientosPorMaestros(MaestroMaterias mmaterias) {
        System.out.println("Entra al metodo de buscar tratamientos");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessionUsuario = request.getSession();
        int idEmployed=(int)sessionUsuario.getAttribute("idEmpleado");
        List<BitacoraRecibos> listBita = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from BitacoraRecibos as m inner join fetch m.alumnos left join fetch m.maestroMaterias "
                + "left join fetch m.menuTratamientos where m.maestroMaterias.clave = '" + mmaterias.getClave() + "' and m.maestroMaterias.maestro.idEmpleado=" + idEmployed;
        try {
            listBita = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        System.out.println("retorna la lista de tamaño "+listBita.size());
        return listBita;

    }

    @Override
    public List<BitacoraRecibos> mostrarTratamientosPorDia(int mes) {

        List<BitacoraRecibos> listBita = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from BitacoraRecibos as m inner join fetch m.maestroMaterias left join fetch "
                + "m.alumnos left join fetch m.menuTratamientos where day(m.fecha)=" + mes;
        try {
            listBita = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listBita;

    }

}
