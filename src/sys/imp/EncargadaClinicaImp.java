/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoEncargadaDeFarmacia;
import sys.model.EncargadaClinica;
import sys.util.HibernateUtil;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class EncargadaClinicaImp implements daoEncargadaDeFarmacia {

    @Override
    public boolean insertarEncargada(EncargadaClinica encargada) {
        boolean inserto=false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(encargada);
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
    public boolean actualizarEncargada(EncargadaClinica encargada) {
        boolean actualizo=false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(encargada);
            session.getTransaction().commit();
            actualizo=true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return actualizo;
    }

    @Override
    public boolean eliminarEncargada(EncargadaClinica encargada) {
        boolean elimino=false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //session.delete(encargada);
            session.createQuery("delete from EncargadaClinica enC where enC.idEmpleado=:idEmpleado").setParameter("idEmpleado", encargada.getIdEmpleado()).executeUpdate();
            session.createQuery("delete from SesionTable st where st.idEmpleado=:idEmpleado").setParameter("idEmpleado", encargada.getIdEmpleado()).executeUpdate();
            session.getTransaction().commit();
            elimino=true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return elimino;
    }

    @Override
    public List<EncargadaClinica> verEncargadas() {
        List<EncargadaClinica> encargada = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From EncargadaClinica";
        try {
            encargada = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return encargada;
    }

}
