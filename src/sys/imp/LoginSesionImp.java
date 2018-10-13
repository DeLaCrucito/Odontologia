/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoLoginSesion;
import sys.model.EncargadaClinica;
import sys.model.Maestro;
import sys.model.SesionTable;
import sys.util.HibernateUtil;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 *
 * @author RamsesMtnz
 */
public class LoginSesionImp implements daoLoginSesion {

    private MessageDigest md = null;
    String contraNOencriptada, contraEncriptada;

    @Override
    public int consultarUsuario(SesionTable loguin) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessionHTTP = request.getSession();
        contraNOencriptada = loguin.getContraseña();
        contraEncriptada = encriptado(contraNOencriptada);
        int usuario = 0;
        List<SesionTable> usuarioList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From SesionTable";
        System.out.println("usuario: "+loguin.getUser()+"\ncontraseña: "+loguin.getContraseña());
        try {
            usuarioList = session.createQuery(hql).list();
            for (SesionTable users : usuarioList) {
                if (users.getContraseña().equals(contraEncriptada) && users.getUser().equals(loguin.getUser())) {
                    sessionHTTP.setAttribute("idEmpleado", users.getIdEmpleado());
                    usuario = users.getTipo();
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

    public String encriptado(String password) {
        // System.out.print("recibe como pasword: "+password);
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        System.out.println("retorna contraseña encriptada: " + sb.toString());
        return sb.toString();
    }

    //hacer el mapeo de la nueva tabla de loguinAlumno, aparte cambiar en la interfaz que sea de tipo usuarioAlumno,
    //y cambiar que la consulta sea igual en el nuevo mapeo.
    @Override
    public int consultarDocente(Maestro docente, SesionTable sesion) {
        contraNOencriptada = sesion.getContraseña();
        contraEncriptada = encriptado(contraNOencriptada);
        int usuario = 0;
        List<Maestro> usuarioList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            usuarioList = session.createQuery(" from Maestro where idEmpleado=:idEmpleado").setParameter("idEmpleado", docente.getIdEmpleado()).list();

            for (Maestro maestro : usuarioList) {
                if (maestro.getIdEmpleado() == docente.getIdEmpleado()) {
                    System.out.println("El maestro ha sido registrado por coordinacion");
                    usuario = 1;
                    //comprobar que sus credenciales no se repiten en el sistema
                    if (credenciales(sesion.getUser())) {
                        session.save(new SesionTable(2, sesion.getUser(), contraEncriptada, docente.getIdEmpleado()));
                        usuario = 2;
                        System.out.println("El maestro ha sido registrado exitosamente");
                    }
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
    public int consultarFarmacia(EncargadaClinica encargada, SesionTable sesion) {
        contraNOencriptada = sesion.getContraseña();
        contraEncriptada = encriptado(contraNOencriptada);
        int usuario = 0;
        List<EncargadaClinica> encargadaList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            encargadaList = session.createQuery(" from EncargadaClinica where idEmpleado=:idEmpleado").setParameter("idEmpleado", encargada.getIdEmpleado()).list();
            for (EncargadaClinica encargadaFarmacia : encargadaList) {
                if (encargadaFarmacia.getIdEmpleado() == encargada.getIdEmpleado()) {
                    System.out.println("El de farmacia ha sido registrado por coordinacion");
                    usuario = 1;
                    if (credenciales(sesion.getUser())) {
                        session.save(new SesionTable(3, sesion.getUser(), contraEncriptada, encargada.getIdEmpleado()));
                        usuario = 2;
                        System.out.println("El ecnargado ha sido registrado exitosamente");
                    }
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

    private boolean credenciales(String user) {
        boolean credencial = true;
        List<SesionTable> userList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From SesionTable";

        try {
            userList = session.createQuery(hql).list();
            for (SesionTable users : userList) {
                if (users.getUser().equals(user)) {
                    System.out.println("Las credenciales de usuario existen");
                    credencial = false;
                } else {
                    System.out.println("Las credenciales de usuario NO existen");
                }
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return credencial;
    }

    @Override
    public int editarPerfil(SesionTable user, String userNew, String passNew) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Object id = request.getSession().getAttribute("idEmpleado");
        int idEmp = (int) id;
        System.out.println("el id del empleado es: " + id.toString());
        int resultado = 0;
        int i = consultarUsuario(user);
        if (i > 0) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String setear = "update SesionTable set usuario =:usuario , contraseña =:contra where id_empleado =:empleado";
            try {
                Query query = session.createQuery(setear);
                query.setParameter("usuario", userNew);
                query.setParameter("contra", encriptado(passNew));
                query.setParameter("empleado", idEmp);
                int result = query.executeUpdate();
                System.out.println("Realiza el update");
                System.out.println("usuario: "+userNew+"\ncontra: "+passNew+"\nID: "+idEmp);
                resultado = 1;
                transaction.commit();
                session.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } else {
            System.out.println("no coinciden las credenciales");
        }
        return resultado;
    }

}
