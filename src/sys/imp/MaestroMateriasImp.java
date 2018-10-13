/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoMaestroMaterias;
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
public class MaestroMateriasImp implements daoMaestroMaterias {

    @Override
    public List<MaestroMaterias> mostrarMaestroMaterias() {
        List<MaestroMaterias> mostrarMaMa = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from MaestroMaterias as m inner join fetch m.maestro left join fetch m.materias";
        try {
            mostrarMaMa = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return mostrarMaMa;
    }

    @Override
    public List<MaestroMaterias> mostrarMaterias(MaestroMaterias maestro) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessionUsuario = request.getSession();
        int user = (int) sessionUsuario.getAttribute("idEmpleado");
        List<MaestroMaterias> mostrarMaMa = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from MaestroMaterias as m inner join fetch m.materias where id_empleado='" + user + "'";
        try {
            Query query = session.createQuery(hql);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            mostrarMaMa = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return mostrarMaMa;
    }

    @Override
    public List<MaestroMaterias> mostrarGrupo(MaestroMaterias maestro) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessionUsuario = request.getSession();
        int user = (int) sessionUsuario.getAttribute("idEmpleado");
        List<MaestroMaterias> mostrarGrupo = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from MaestroMaterias as m inner join fetch m.materias where id_empleado='" + user + "' and id_materia=" + maestro.getMaterias().getIdMateria();
        try {

            Query query = session.createQuery(hql);
            query.setResultTransformer(Criteria.PROJECTION);
            mostrarGrupo = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            transaction.rollback();
        }

        return mostrarGrupo;
    }

    @Override
    public List<MaestroMaterias> mostrarGrado(MaestroMaterias maestro) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessionUsuario = request.getSession();
        int user = (int) sessionUsuario.getAttribute("idEmpleado");
        List<MaestroMaterias> mostrarGrado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from MaestroMaterias as m inner join fetch m.materias where id_empleado='" + user + "' and id_materia=" + maestro.getMaterias().getIdMateria();
        try {
            mostrarGrado = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            transaction.rollback();
        }

        return mostrarGrado;
    }

    @Override
    public List<MaestroMaterias> mostrarMateriasAsignadas() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessionUsuario = request.getSession();
        int user = (int) sessionUsuario.getAttribute("idEmpleado");
        List<MaestroMaterias> mostrarMaterias = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from MaestroMaterias as m inner join fetch m.materias where id_empleado='" + user + "'";
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

   
}
