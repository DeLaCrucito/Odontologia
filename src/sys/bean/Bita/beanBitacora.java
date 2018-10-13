/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean.Bita;

import sys.dao.daoBitacora;
import sys.imp.BitacoraImp;
import sys.model.Alumnos;
import sys.model.BitacoraRecibos;
import sys.model.MaestroMaterias;
import sys.model.MenuTratamientos;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Named(value = "beanBitacora")
@ViewScoped
public class beanBitacora implements Serializable {

    /**
     * Creates a new instance of beanBitacora
     */
    private BitacoraRecibos bitacora;
    private List<BitacoraRecibos> bita;
    private List<BitacoraRecibos> bitaM;
    private List<BitacoraRecibos> bitaDia;
    private MaestroMaterias mmaterias;
    private Alumnos alumno;
    private MenuTratamientos tratamientos;

    public beanBitacora() {
        bitacora = new BitacoraRecibos();
        alumno = new Alumnos();
        mmaterias = new MaestroMaterias();
        tratamientos = new MenuTratamientos();
    }

    public BitacoraRecibos getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraRecibos bitacora) {
        this.bitacora = bitacora;
    }

    public List<BitacoraRecibos> getBita() {
        return bita;
    }

    public void setBita(List<BitacoraRecibos> bita) {
        this.bita = bita;
    }

    public MaestroMaterias getMmaterias() {
        return mmaterias;
    }

    public void setMmaterias(MaestroMaterias mmaterias) {
        this.mmaterias = mmaterias;
    }

    public List<BitacoraRecibos> getBitaM() {
        return bitaM;
    }

    public List<BitacoraRecibos> getBitaDia() {
        Calendar fecha = Calendar.getInstance();
        int mes = fecha.get(Calendar.DAY_OF_MONTH);
        daoBitacora bdao = new BitacoraImp();
        bitaDia = bdao.mostrarTratamientosPorDia(mes);
        return bitaDia;
    }

    public void setBitaM(List<BitacoraRecibos> bitaM) {
        this.bitaM = bitaM;
    }

    public void verTratamientosClase() {
        daoBitacora bdao = new BitacoraImp();
        bitaM = bdao.mostrarTratamientosPorMaestros(mmaterias);
    }

    public void verTratamientos() {
        daoBitacora bdao = new BitacoraImp();
        bita = bdao.mostratTratamientosPorAlumno(mmaterias);
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public MenuTratamientos getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(MenuTratamientos tratamientos) {
        this.tratamientos = tratamientos;
    }
    
    

}
