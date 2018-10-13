/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoAlumnos;
import sys.model.Alumnos;
import sys.util.HibernateUtil;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AlumnosImp implements daoAlumnos {

    String contraNOencriptada, contraEncriptada;
    LoginSesionImp encriptadoLoguin = new LoginSesionImp();

    @Override
    public int insertarAlumnos(Alumnos alumnos) {
        String contraseñaEncriptada = encriptadoLoguin.encriptado(alumnos.getContraseña());
        int seRegistro = 0;
        Session session = null;
        List<Alumnos> usuarioList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            usuarioList = session.createQuery("From Alumnos").list();

            for (Alumnos users : usuarioList) {
                if (users.getUsuario().equals(alumnos.getUsuario())) {
                    //si el usuario que esta guardando es igual a alguno de la base de datos
                    seRegistro = 1;
                    System.out.println("mismos usuarios");
                } else if (users.getMatricula() == alumnos.getMatricula()) {
                    // si coincide con alguna matricula ya registrada
                    seRegistro = 2;
                    System.out.println("mismas matriculas");
                }
            }
            if (seRegistro == 0) {
                System.out.println("guarda datos");
                session.save(new Alumnos(alumnos.getMatricula(), alumnos.getNombre(), alumnos.getApellidoPaterno(), alumnos.getApellidoMaterno(), alumnos.getUsuario(), contraseñaEncriptada, alumnos.getSemestre(), alumnos.getGrupo()));
                session.getTransaction().commit();
                seRegistro = 3;
                System.out.println("datos guardados");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        System.out.println("retorna entero" + seRegistro);
        return seRegistro;
    }

    @Override
    public boolean consultarAlumno(Alumnos loguin) {
        contraEncriptada = encriptadoLoguin.encriptado(loguin.getContraseña());
        boolean usuario = false;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessionUsuario = request.getSession();
        List<Alumnos> usuarioList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Alumnos";

        try {
            usuarioList = session.createQuery(hql).list();
            for (Alumnos users : usuarioList) {
                if (users.getContraseña().equals(contraEncriptada) && users.getUsuario().equals(loguin.getUsuario())) {
                    System.out.println("Existe\nMatricula del alumno es: " + users.getMatricula());
                    sessionUsuario.setAttribute("idSesion", users.getMatricula());
                    sessionUsuario.setAttribute("nombreCompletoAlumno",users.getNombre()+" "+users.getApellidoPaterno()
                    +" "+users.getApellidoMaterno());
                    usuario = true;
                }
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return usuario;
    }

    @Override
    public boolean editarPerfil(Alumnos alumno, String newUser, String newPass) {
        System.out.println("sdasdasdasd");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Object id = request.getSession().getAttribute("idSesion");
        int matriculaAlumno = (int) id;
        boolean edicionExitosa = false;        
        List<Alumnos> alumnosList = null;
        System.out.println("usuario nuevo: "+newUser+"\npass nuevo: "+newPass+"\nuser viejo: "+alumno.getUsuario()+"\npass viejo: "+alumno.getContraseña()+"\nmatricula: "+matriculaAlumno);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Alumnos";
        try {
            alumnosList = session.createQuery(hql).list();
            for (Alumnos student : alumnosList) {
                System.out.println(student.getUsuario()+"\n");
                if (student.getUsuario().equals(alumno.getUsuario()) && student.getContraseña().equals(encriptadoLoguin.encriptado(alumno.getContraseña())) && student.getMatricula()== matriculaAlumno) {
                    //credenciales iguales y datos personales igual
                    String setear = "update Alumnos set usuario =:usuario , grupo =:grupo, semestre=:semestre, contraseña=:contra where matricula =:matricula";
                    try {
                        Query query = session.createQuery(setear);
                        query.setParameter("grupo", alumno.getGrupo());
                        query.setParameter("usuario", newUser);
                        query.setParameter("semestre", alumno.getSemestre());
                        query.setParameter("contra", encriptadoLoguin.encriptado(newPass));
                        query.setParameter("matricula", matriculaAlumno);
                        int result = query.executeUpdate();
                        System.out.println("Realiza el update");
                        edicionExitosa = true;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        transaction.rollback();
                    }
                }else{
                    System.out.println("No son inguales ningun dato");
                }
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    return edicionExitosa;
    }

}
