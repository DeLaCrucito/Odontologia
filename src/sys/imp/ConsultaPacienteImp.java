/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoConsultaPaciente;
import sys.model.pacientes.Consultas;
import sys.model.pacientes.ControlPlaca;
import sys.model.pacientes.Paciente;
import sys.model.pacientes.Periodontograma;
import sys.util.HibernateUtil;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Ramses Mtnz Santiago
 */
public class ConsultaPacienteImp implements daoConsultaPaciente {

    @Override
    public List<Paciente> verFoliosPaciente(Paciente paciente) {
        List<Paciente> listPacientes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Paciente as p where p.folio like '%" + paciente.getFolio() + "%'";
        try {
            listPacientes = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listPacientes;
    }

    @Override
    public List<Paciente> verNombresPaciente(Paciente paciente) {
        List<Paciente> listPacientes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Paciente as p where p.nombre like '%" + paciente.getNombre() + "%'";
        try {
            listPacientes = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        System.out.println(listPacientes.size());
        return listPacientes;
    }

    @Override
    public boolean insertaConsulta(ControlPlaca control, Periodontograma periodonto, Paciente paciente) {
        boolean inserto = false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            int idPaciente = paciente.getId();
            control.setEstado("subsecuente");
            System.out.println(control.getDientes());
            control.setDientes(control.getDientes());
            control.setPaciente(paciente);
            session.save(control);

            periodonto.setEstado("subsecuente");
            periodonto.setPaciente(paciente);
            System.out.println(control.getDientes());
            periodonto.setDientes(periodonto.getDientes());
            session.save(periodonto);

            Consultas consultas = new Consultas();
            consultas.setControlPlaca(control);
            consultas.setFolio(paciente.getFolio());
            consultas.setFecha(new Date());
            consultas.setPaciente(paciente);
            consultas.setPeriodontograma(periodonto);
            session.save(consultas);

            session.getTransaction().commit();

            inserto = true;

        } catch (HibernateException e) {

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
    public List<Consultas> verConsultas(int paciente) {
        List<Consultas> listaConsultas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int idPaciente = paciente;
        String hql = "from Consultas as c where c.paciente= " + idPaciente;
        try {
            listaConsultas = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaConsultas;
    }

    @Override
    public String verPlaca(ControlPlaca controlPlaca) {
        int id = controlPlaca.getId();
        String cadena = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            ControlPlaca control = (ControlPlaca) session.get(ControlPlaca.class, id);
            cadena = control.getDientes();
            controlPlaca.setNumDientes(control.getNumDientes());
            controlPlaca.setNumSuperficies(control.getNumSuperficies());
            controlPlaca.setSuperficiesPlaca(control.getSuperficiesPlaca());
            controlPlaca.setExtensionPlaca(control.getExtensionPlaca());
            System.out.println(cadena);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {

            if (session != null) {
                session.close();
            }
        }
        return cadena;

    }



    @Override
    public String verPeriodontograma(Periodontograma periodontograma) {
     
        int id = periodontograma.getId();
        String cadena = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Periodontograma periodonto = (Periodontograma) session.get(Periodontograma.class, id);
            cadena = periodonto.getDientes();
            System.out.println(cadena);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return cadena;
    }

    @Override
    public boolean eliminarExpediente(int consultas){
        boolean elimino = false;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete from Consultas Cons where Cons.id=:id").setParameter("id",consultas).executeUpdate();
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
    public boolean eliminarPacientes(int paciente){
        boolean elimino = false;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete from Paciente p where p.id=:id").setParameter("id",paciente).executeUpdate();
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
}
