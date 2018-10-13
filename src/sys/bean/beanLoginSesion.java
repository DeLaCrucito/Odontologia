/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import sys.dao.daoLoginSesion;
import sys.imp.LoginSesionImp;
import sys.model.Alumnos;
import sys.model.EncargadaClinica;
import sys.model.Maestro;
import sys.model.SesionTable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author RamsesMtnz
 */
@Named(value = "LoginMB")
@ViewScoped
public class beanLoginSesion implements Serializable {

    private String newUser, newPass;
    private SesionTable user;
    private Maestro maestro;
    private Alumnos alumno;
    private EncargadaClinica encargada;
    private boolean logeado = false;

    public beanLoginSesion() {
        user = new SesionTable();
        alumno = new Alumnos();
        maestro = new Maestro();
        encargada = new EncargadaClinica();
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

    
    /**
     * @return the encargada
     */
    public EncargadaClinica getEncargada() {
        return encargada;
    }

    /**
     * @param encargada the encargada to set
     */
    public void setEncargada(EncargadaClinica encargada) {
        this.encargada = encargada;
    }

    /**
     * @return the alumno
     */
    public Alumnos getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the maestro
     */
    public Maestro getMaestro() {
        return maestro;
    }

    /**
     * @param maestro the maestro to set
     */
    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }

    public boolean estaLogeado() {
        return logeado;
    }

    /**
     * @return the usuario
     */
    public SesionTable getUsuario() {
        return user;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(SesionTable usuario) {
        this.user = usuario;
    }

    public void insertarFarmacia() {
        daoLoginSesion daoSesion = new LoginSesionImp();
        int existe = daoSesion.consultarFarmacia(encargada, user);
        mensajes(existe);
    }

    public void insertarDocente() {
        daoLoginSesion daoSesion = new LoginSesionImp();
        int existe = daoSesion.consultarDocente(maestro, user);
        mensajes(existe);

    }

    public void login(ActionEvent actionEvent) throws IOException {
        daoLoginSesion daoSesion = new LoginSesionImp();
        int tipoUsuario = daoSesion.consultarUsuario(user);
        System.out.println("id del empleado desde el loguin: " + user.getIdEmpleado());
        switch (tipoUsuario) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Lo sentimos, no aparece en el sistema"));
                break;
            case 1:
                logeado = true;
                responseAndRequest("/FdO-3.0/pages/Coordinadora/Principal.xhtml", "tokenCoordinacion");
                break;
            case 2:
                logeado = true;
                responseAndRequest("/FdO-3.0/pages/Maestro/Principal.xhtml", "tokenDocente");
                break;
            case 3:
                logeado = true;
                responseAndRequest("/FdO-3.0/pages/EncargadaDeFarmacia/Principal.xhtml", "tokenFarmacia");
                break;
            default:
                break;
        }

    }

    //Hacer un logout unico que reciba el string del atributo que va a remover y no tener mas codigo
    public void logoutCoordinacion(ActionEvent actionEvent) throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("tokenCoordinacion");
        logeado = false;
        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/FdO-3.0/pages/Login.xhtml");
    }

    public void logoutFarmacia(ActionEvent actionEvent) throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("tokenFarmacia");
        logeado = false;
        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/FdO-3.0/pages/Login.xhtml");
    }

    public void logoutDocente(ActionEvent actionEvent) throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("tokenDocente");
        logeado = false;
        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/FdO-3.0/pages/Login.xhtml");
    }

    private void responseAndRequest(String ruta, String tokenName) throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(tokenName, user.getUser());
        System.out.println("El usuario del token es: " + user.getUser());
        HttpServletResponse sResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        sResponse.sendRedirect(ruta);
    }

    private void mensajes(int i) {
        switch (i) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se ha dado de alta desde dirección"));
                break;
            case 1:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario ya existente", "El usuario ya existe en el sistema. Cambielos por favor."));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Felicidades!", "Ha sido registrado de manera exitosa al sistema."));
                break;
            default:
                break;
        }
    }

    public void editarPerfil() {

        daoLoginSesion daoSesion = new LoginSesionImp();
        int tipoUsuario = daoSesion.editarPerfil(user, newUser, newPass);
        switch (tipoUsuario) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales", "El usuario o la contraseña son incorrectos. Verífiquelos"));
                break;
            case 1:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales", "Se ha actualizado sus datos de sesión."));
                break;
            default:
                break;
        }

//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        HttpSession sessionUsuario = request.getSession();
//        Object id=request.getSession().getAttribute("idEmpleado");
//        System.out.println("el id del empleado es: "+id.toString());
    }

}
