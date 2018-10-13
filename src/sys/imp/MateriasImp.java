/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoMaterias;
import sys.model.Materias;
import sys.util.HibernateUtil;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MateriasImp implements daoMaterias {

    @Override
    public void insertarMaterias(Materias materia) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(materia);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Materias> mostrarMaterias() {
        List<Materias> mostrarMaterias = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Materias order by semestre";
        try {
            mostrarMaterias = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            transaction.rollback();
        }

        return mostrarMaterias;
    }

    @Override
    public boolean actuliazarMaterias(Materias materia) {
        boolean editar = false;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(materia);
            session.getTransaction().commit();
            editar = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return editar;
    }

    @Override
    public boolean eliminarMateria(Materias materia) {
        boolean eliminar = false;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(materia);
            session.getTransaction().commit();
            eliminar = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return eliminar;
    }

   
}
