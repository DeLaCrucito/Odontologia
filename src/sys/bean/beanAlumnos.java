/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import javafx.event.ActionEvent;
import sys.dao.daoAlumnos;
import sys.imp.AlumnosImp;
import sys.model.Alumnos;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Named(value = "beanAlumnos")
@ViewScoped
public class beanAlumnos implements Serializable {

    private Alumnos alumnos;
    private String newUser, newPass;

    public beanAlumnos() {
        alumnos = new Alumnos();
    }

    /**
     * @return the newUser
     */
    public String getNewUser() {
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(String newUser) {
        this.newUser = newUser;
    }

    /**
     * @return the newPass
     */
    public String getNewPass() {
        return newPass;
    }

    /**
     * @param newPass the newPass to set
     */
    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public Alumnos getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumnos alumnos) {
        this.alumnos = alumnos;
    }

    public void cancelar() {
        alumnos = new Alumnos();
    }

    public void insertarAlumno() throws IOException {
        daoAlumnos adao = new AlumnosImp();
        int registro = adao.insertarAlumnos(alumnos);
        alumnos = new Alumnos();
        switch (registro) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error del Sistema o del servidor"));
                break;
            case 1:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Advertencia!", "El usuario ya se encuentra registrado."));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ya se encuentra un alumno con la misma matricula registrado."));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Felicidades!", "Bienvenido al portal de odontología."));
                //responseAndRequest("/FdO-3.0/pages/EncargadaDeFarmacia/Principal.xhtml", "tokenFarmacia");
                break;
            default:
                break;
        }
    }

    public void loguinAlumno(ActionEvent action) throws IOException {
        daoAlumnos daoSesion = new AlumnosImp();
        boolean existeAlumno = daoSesion.consultarAlumno(alumnos);
        if (existeAlumno) {
            responseAndRequestAlumno("/FdO-3.0/pages/Alumnos/Principal.xhtml", "tokenAlumno");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Lo sentimos, no aparece en el sistema"));
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("tokenAlumno");
        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/FdO-3.0/pages/LoginAlumno.xhtml");
    }

    private void responseAndRequestAlumno(String ruta, String tokenName) throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(tokenName, alumnos.getUsuario());
        System.out.println("El usuario del token es: " + alumnos.getUsuario());
        HttpServletResponse sResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        sResponse.sendRedirect(ruta);
    }

    public void editarPerfil(ActionEvent actionEvent) throws IOException {
        daoAlumnos daoSesion = new AlumnosImp();
        boolean actualizoDatos = daoSesion.editarPerfil(alumnos, newUser, newPass);
        if (actualizoDatos) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Sus datos han sido actualizados exitosamente."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Sus datos no coinciden, verifíquelos e intente de nuevo."));
        }
    }

    public void editar() {
        System.out.println("entra al metodo");
        daoAlumnos daoSesion = new AlumnosImp();
        System.out.println("usuario: "+newUser+"\npass: "+newPass);
        boolean existeAlumno = daoSesion.editarPerfil(alumnos,newUser,newPass);
        if (existeAlumno) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Lo sentimos, no aparece en el sistema"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Lo sentimos, no aparece en el sistema"));
        }
    }

}
