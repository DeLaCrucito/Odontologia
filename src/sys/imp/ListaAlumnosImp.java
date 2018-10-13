/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoListaAlumnos;
import sys.model.Alumnos;
import sys.model.ListaAlumnos;
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
public class ListaAlumnosImp implements daoListaAlumnos {

    

    @Override
    public List<ListaAlumnos> MostrarAlumnos(MaestroMaterias mmaterias) {
        List<ListaAlumnos> mostrarAlumnos = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from ListaAlumnos as m inner join fetch m.maestroMaterias left join fetch m.alumnos where m.maestroMaterias.año='" + mmaterias.getAño() + "' and "
                + "m.maestroMaterias.materias.idMateria=" + mmaterias.getMaterias().getIdMateria() + " and m.maestroMaterias.grupo=" + mmaterias.getGrupo();

        try {
            mostrarAlumnos = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return mostrarAlumnos;
    }

    @Override
    public boolean insertarListaAlumnos(ListaAlumnos lista) {
        boolean inserto=false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(lista);
            session.getTransaction().commit();
            inserto=true;
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
    public List<ListaAlumnos> MostrarClases(Alumnos alumnos, MaestroMaterias mmaterias) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpSession sessionUsuario = request.getSession();
    int user = (int) sessionUsuario.getAttribute("idSesion");
        List<ListaAlumnos> mostrarClases = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from ListaAlumnos as m inner join fetch m.maestroMaterias left join fetch m.alumnos where m.alumnos.matricula="+user;

        try {
            mostrarClases = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return mostrarClases;
    }

}
