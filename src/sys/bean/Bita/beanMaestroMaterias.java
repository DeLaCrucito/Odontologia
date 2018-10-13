/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean.Bita;

import sys.dao.daoListaAlumnos;
import sys.dao.daoMaestroMaterias;
import sys.imp.ListaAlumnosImp;
import sys.imp.MaestroMateriasImp;
import sys.model.ListaAlumnos;
import sys.model.Maestro;
import sys.model.MaestroMaterias;
import sys.model.Materias;

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
@Named(value = "beanMaestroMaterias")
@ViewScoped
public class beanMaestroMaterias implements Serializable {

    /**
     * Creates a new instance of beanMaestroMaterias
     */
    private MaestroMaterias maestroMaterias;
    private List<MaestroMaterias> maestroM;
    private Maestro maestro;
    private Materias materias;
    private List<SelectItem> listarMaterias;
    private List<SelectItem> listarGrupo;
    private List<SelectItem> listarGrado;
    private ListaAlumnos lista;
    private List<ListaAlumnos> listarAlumnos;
    private List<MaestroMaterias> listarMateriasAsignadas;

    public beanMaestroMaterias() {
        maestroMaterias = new MaestroMaterias();
        maestro = new Maestro();
        materias = new Materias();
        lista = new ListaAlumnos();
        
    }

    public MaestroMaterias getMaestroMaterias() {
        return maestroMaterias;
    }

    public void prepararNuevoMaestroM(ActionEvent actionEvent) {
        maestroMaterias = new MaestroMaterias();
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

    public void setMaestroMaterias(MaestroMaterias maestroMaterias) {
        this.maestroMaterias = maestroMaterias;
    }

    public List<MaestroMaterias> getMaestroM() {
        daoMaestroMaterias mdao = new MaestroMateriasImp();
        maestroM = mdao.mostrarMaestroMaterias();
        return maestroM;
    }

    public List<MaestroMaterias> getListarMateriasAsignadas() {
         daoMaestroMaterias mdao = new MaestroMateriasImp();
        listarMateriasAsignadas = mdao.mostrarMateriasAsignadas();
        return listarMateriasAsignadas;
    }

    public void setListarMateriasAsignadas(List<MaestroMaterias> listarMateriasAsignadas) {
        this.listarMateriasAsignadas = listarMateriasAsignadas;
    }
    
     

   
     
     
    public ListaAlumnos getLista() {
        return lista;
    }

    public void setLista(ListaAlumnos lista) {
        this.lista = lista;
    }

    
    
    public List<ListaAlumnos> getListarAlumnos() {
        return listarAlumnos;
    }

    public void setListarAlumnos(List<ListaAlumnos> listarAlumnos) {
        this.listarAlumnos = listarAlumnos;
    }

    public void buscarLista() {
        daoListaAlumnos ldao = new ListaAlumnosImp();
        listarAlumnos = ldao.MostrarAlumnos(maestroMaterias);
    }

    public List<SelectItem> getListarMaterias() {
        this.listarMaterias = new ArrayList<SelectItem>();
        daoMaestroMaterias mdao = new MaestroMateriasImp();
        List<MaestroMaterias> m = mdao.mostrarMaterias(this.maestroMaterias);

        List<Materias> dto = new ArrayList<>();
        m.forEach((MaestroMaterias e) -> {
            if (!dto.contains(e.getMaterias())) {
                dto.add(e.getMaterias());
            }
        });

        listarMaterias.clear();
        for (Materias materias : dto) {
            SelectItem materiaItem = new SelectItem(materias.getIdMateria(), materias.getMateria());
            this.listarMaterias.add(materiaItem);
        }

        return listarMaterias;
    }

    public List<SelectItem> getListarGrupo() {
        this.listarGrupo = new ArrayList<SelectItem>();
        daoMaestroMaterias mdao = new MaestroMateriasImp();
        List<MaestroMaterias> m = mdao.mostrarGrupo(this.maestroMaterias);

        List<Materias> dto = new ArrayList<>();
        m.forEach((MaestroMaterias e) -> {
            if (!dto.contains(e.getMaterias())) {
                dto.add(e.getMaterias());
            }
        });

        listarGrupo.clear();
        for (Materias grupo : dto) {
            SelectItem materiaItem = new SelectItem(grupo.getSemestre());
            this.listarGrupo.add(materiaItem);
        }

        return listarGrupo;
    }

    
    
    public List<SelectItem> getListarGrado() {
        this.listarGrado = new ArrayList<SelectItem>();
        daoMaestroMaterias mdao = new MaestroMateriasImp();
        List<MaestroMaterias> m = mdao.mostrarGrupo(this.maestroMaterias);
        listarGrado.clear();
        for (MaestroMaterias grupo : m) {
            SelectItem materiaItem = new SelectItem(grupo.getAño());
            this.listarGrado.add(materiaItem);
        }
        return listarGrado;
    }
    
    
}
