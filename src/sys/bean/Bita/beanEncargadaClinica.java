/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean.Bita;

import sys.dao.daoEncargadaDeFarmacia;
import sys.imp.EncargadaClinicaImp;
import sys.model.EncargadaClinica;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Named(value = "beanEncargadaClinica")
@ViewScoped
public class beanEncargadaClinica implements Serializable {

    private EncargadaClinica encargada;
    private List<EncargadaClinica> verEncargada;

    public beanEncargadaClinica() {
        encargada = new EncargadaClinica();
    }

    public EncargadaClinica getEncargada() {
        return encargada;
    }

    public void setEncargada(EncargadaClinica encargada) {
        this.encargada = encargada;
    }

    public void insertarEncargadaClinica() {
        daoEncargadaDeFarmacia edao = new EncargadaClinicaImp();
        boolean inserto=edao.insertarEncargada(encargada);
        if(inserto){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se a ingresado satisfactoriamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El registro no se puede insertar en este momento. Intente más tarde o contacte a soporte técnico."));
        }
    }

    public List<EncargadaClinica> getVerEncargada() {
        daoEncargadaDeFarmacia edao = new EncargadaClinicaImp();
        verEncargada = edao.verEncargadas();
        return verEncargada;
    }

    public void actualizarEncargada() {
        daoEncargadaDeFarmacia edao = new EncargadaClinicaImp();
        boolean actaliza=edao.actualizarEncargada(encargada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se a actualizado satisfactoriamente"));
    }

    public void eliminarEncargada() {
        daoEncargadaDeFarmacia edao = new EncargadaClinicaImp();
        boolean elimino=edao.eliminarEncargada(encargada);
        if(elimino){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se a eliminado satisfactoriamente"));
        }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El registro no se puede eliminar en este momento. Intente más tarde o contacte a soporte técnico.")); 
        }
    }

}
