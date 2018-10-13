/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean.Bita;

import sys.dao.daoMaterias;
import sys.imp.MateriasImp;
import sys.model.Materias;

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
@Named(value = "beanMaterias")
@ViewScoped
public class beanMaterias implements Serializable {

    /**
     * Creates a new instance of beanMaterias
     */
    private Materias materia;
    private List<Materias> listarMaterias;

    public beanMaterias() {
        materia = new Materias();
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

    public void insertarMateria() {
        daoMaterias mdao = new MateriasImp();
        mdao.insertarMaterias(materia);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se a ingresado satisfactoriamente"));

    }
    
     public void actualziarMaterias() {
        daoMaterias mdao = new MateriasImp();
        boolean actualizo=mdao.actuliazarMaterias(materia);
        materia = new Materias();
        if(actualizo){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se ha actualizado satisfactoriamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El registro no se puede editar en este momento. Intente más tarde o contacte a soporte técnico."));
        }
    }

    public void eliminarMaterias() {
        daoMaterias mdao = new MateriasImp();
        boolean borro=mdao.eliminarMateria(materia);
        if(borro){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se ha eliminado satisfactoriamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El registro no se puede eliminar en este momento. Intente más tarde o contacte a soporte técnico."));
        }
    }
    
     public List<Materias> getListar() {
        daoMaterias mdao = new MateriasImp();
        listarMaterias = mdao.mostrarMaterias();
        return listarMaterias;
    }
}
