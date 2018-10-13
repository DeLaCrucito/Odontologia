/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean.Bita;

import clases.Clave;
import sys.dao.daoMaestro;
import sys.imp.MaestroImp;
import sys.model.Maestro;
import sys.model.MaestroMaterias;
import sys.model.Materias;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Named(value = "beanMaestro")
@ViewScoped
public class beanMaestro implements Serializable {

    private Maestro maestro;
    private MaestroMaterias maestroMaterias;
    private Materias materias;
    private List<Maestro> listar;

    private List<SelectItem> listarMaterias;
    private List<SelectItem> listarMaestros;
    private String clave;

    public beanMaestro() {
        maestro = new Maestro();
        maestroMaterias = new MaestroMaterias();
        materias = new Materias();
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }

    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }

    public MaestroMaterias getMaestroMaterias() {
        return maestroMaterias;
    }

    public void setMaestroMaterias(MaestroMaterias maestroMaterias) {
        this.maestroMaterias = maestroMaterias;
    }

    public void prepararNuevoMaestro(ActionEvent actionEvent) {
        maestro = new Maestro();
    }

    public String getClave() {
        return clave;
    }

    public void codigo() {
        daoMaestro mdao = new MaestroImp();
        clave = mdao.mostrarCodigo();

    }
    
    
    public void insertarMaestro() {
        daoMaestro mdao = new MaestroImp();
        boolean inserto = mdao.insertarMaestro(maestro);
        if (inserto) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se ha ingresado satisfactoriamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al guardar datos", "El registro no se puede guardar en este momento. Intente más tarde o contacte a soporte técnico."));
        }
    }

    public List<Maestro> getListar() {
        daoMaestro mdao = new MaestroImp();
        listar = mdao.mostrarMaestro();
        return listar;
    }

    public void actualizarMaestro() {
        daoMaestro mdao = new MaestroImp();
        boolean actualizo = mdao.editarMaestro(maestro);
        maestro = new Maestro();
        if (actualizo) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se ha actualizado satisfactoriamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El registro no se puede editar en este momento. Intente más tarde o contacte a soporte técnico."));
        }
    }

    public void eliminarMaestro() {
        daoMaestro mdao = new MaestroImp();
        boolean borro = mdao.eliminarMaestro(maestro);
        if (borro) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se ha eliminado satisfactoriamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El registro no se puede eliminar en este momento. Intente más tarde o contacte a soporte técnico."));
        }
    }

    public List<SelectItem> getListMaestro() {
        this.listarMaestros = new ArrayList<SelectItem>();
        daoMaestro mdao = new MaestroImp();
        List<Maestro> m = mdao.mostrarMaestro();
        listarMaestros.clear();
        for (Maestro g : m) {
            SelectItem gradoItem = new SelectItem(g.getIdEmpleado(), g.getNombre() + " " + g.getApellidoPaterno() + " " + g.getApellidoMaterno());
            this.listarMaestros.add(gradoItem);
        }
        return listarMaestros;
    }

    public List<SelectItem> getListarMaterias() {
        this.listarMaterias = new ArrayList<SelectItem>();
        daoMaestro mdao = new MaestroImp();
        List<Materias> m = mdao.mostrarMaterias(this.maestroMaterias);
        listarMaterias.clear();
        for (Materias materia : m) {
            SelectItem materiaItem = new SelectItem(materia.getIdMateria(), materia.getMateria());
            this.listarMaterias.add(materiaItem);
        }

        return listarMaterias;
    }

    public void insertarMaestroMaterias() {
        daoMaestro mdao = new MaestroImp();
        Clave cla = new Clave();
        maestroMaterias.setClave(cla.clave());
        boolean exito = mdao.insertarMaestroMaterias(maestroMaterias);
        if (exito) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se ha ingresado satisfactoriamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Datos duplicados", "El registro o docente se encuentra registrado previamente"));
        }
    }
    
     public void eliminarMateriaMaestro() {
        daoMaestro mdao = new MaestroImp();
        boolean borro=mdao.eliminarMateriaMaestro(maestroMaterias);
        if(borro){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se ha eliminado satisfactoriamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El registro no se puede eliminar en este momento. Intente más tarde o contacte a soporte técnico."));
        }
    }


    public void cancelar() {
        maestro = new Maestro();

    }
}
