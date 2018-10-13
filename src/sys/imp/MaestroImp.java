/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoMaestro;
import sys.model.Maestro;
import sys.model.MaestroMaterias;
import sys.model.Materias;
import sys.util.HibernateUtil;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MaestroImp implements daoMaestro {

    @Override
    public boolean insertarMaestro(Maestro maestro) {
        boolean inserto = false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(maestro);
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
    public List<Maestro> mostrarMaestro() {
        List<Maestro> mostrarMaestro = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Maestro";
        try {
            mostrarMaestro = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return mostrarMaestro;
    }

    @Override
    public boolean editarMaestro(Maestro maestro) {
        boolean edito = false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(maestro);
            session.getTransaction().commit();
            edito = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return edito;
    }

    @Override
    public boolean eliminarMaestro(Maestro maestro) {
        boolean eliminado = false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.print("EL ID DEL EMPLEADO ES: " + maestro.getIdEmpleado());
            //session.delete(maestro);
            session.createQuery("delete from Maestro m where m.idEmpleado=:idEmpleado").setParameter("idEmpleado", maestro.getIdEmpleado()).executeUpdate();
            session.createQuery("delete from SesionTable st where st.idEmpleado=:idEmpleado").setParameter("idEmpleado", maestro.getIdEmpleado()).executeUpdate();
            session.getTransaction().commit();
            eliminado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return eliminado;
    }

    @Override
    public List<Materias> mostrarMaterias(MaestroMaterias maestroM) {
        List<Materias> mostrarMaterias = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Materias where semestre = " + maestroM.getGrupo();
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
    public boolean insertarMaestroMaterias(MaestroMaterias materia) {
        boolean insertoMateria = false, existe = false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // comprobar que no exista en la base de datos primero
            String hql = "From MaestroMaterias";
            List<MaestroMaterias> docentesMaterias = null;

            docentesMaterias = session.createQuery(hql).list();
            for (MaestroMaterias docentes : docentesMaterias) {
                if (docentes.getMaestro().getIdEmpleado() == materia.getMaestro().getIdEmpleado()
                        && docentes.getGrupo().equals(materia.getGrupo())
                        && docentes.getMaterias().getIdMateria() == materia.getMaterias().getIdMateria()
                        && docentes.getAño().equals(materia.getAño())) {
                    System.out.println("Ya existe un profe dando la misma clase en el mismo salon");
                    existe = true;
                }
            }
            if (!existe) {
                session.save(materia);
                session.getTransaction().commit();
                insertoMateria = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return insertoMateria;
    }

    @Override
    public String mostrarCodigo() {

        String clave = "";
        String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

        int numeroAleatorio1 = (int) (Math.random() * 35 + 1);
        int numeroAleatorio2 = (int) (Math.random() * 35 + 1);
        int numeroAleatorio3 = (int) (Math.random() * 35 + 1);
        int numeroAleatorio4 = (int) (Math.random() * 35 + 1);
        int numeroAleatorio5 = (int) (Math.random() * 35 + 1);
        int numeroAleatorio6 = (int) (Math.random() * 35 + 1);

        clave = abc[numeroAleatorio1].concat(abc[numeroAleatorio2]).concat(abc[numeroAleatorio3]).concat(abc[numeroAleatorio4]).concat(abc[numeroAleatorio5].concat(abc[numeroAleatorio6]));

        return clave;

    }
    
     @Override
    public boolean eliminarMateriaMaestro(MaestroMaterias mmaterias) {
       boolean eliminado = false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(mmaterias);
            session.getTransaction().commit();
            eliminado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return eliminado;
    }


   
}
