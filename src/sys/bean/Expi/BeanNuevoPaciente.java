/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean.Expi;

import net.sf.jasperreports.engine.JRException;
import sys.bean.Bita.beanReportes;
import sys.dao.daoNuevoPaciente;
import sys.imp.NuevoPacienteImp;
import sys.model.pacientes.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ramses Mtnz Santiago
 */
@Named(value = "BeanNuevoPaciente")
@ViewScoped
public class BeanNuevoPaciente implements Serializable {
    
    private  DireccionPaciente dirreccionPaciente;
    private  MedicoPaciente medico;
    private  TrabajoPaciente trabajoPaciente;
    private  Paciente paciente;
    private  String profe;
    private ExamenOral examenOral;
    private String[] habitosTensionList;
    private Atm atm; 
    private AnalisisOclusion analisisOclusion;
    private HallazgosRadiograficos hallazgosRadiograficos;
    private ExamenOrofacial examenOrofacial;
     private  PreguntasPaciente preguntasPaciente;
    private  String[] alergias;
    private  String[] selectedConsoles;
    
    private String numdientes;
    private String Superficiesplaca;
    private String numsuperficies;
    private String extensionplaca;
    private String dientecontrol;
    private String dienteBDcontrol;
    private ControlPlaca control;
    
     private String dienteimg;
    private String dienteBDperi;
    private Periodontograma periodonto;

    @PostConstruct
    protected void init() {
         preguntasPaciente.setP1("Si");
        preguntasPaciente.setP2("Si");
        preguntasPaciente.setP3("Si");
        preguntasPaciente.setP4("Si");
        preguntasPaciente.setP5("Si");
        preguntasPaciente.setP6("Si");
        preguntasPaciente.setP7("Si");
        preguntasPaciente.setP8("Si");
        preguntasPaciente.setP9("Si");
        preguntasPaciente.setP10("Si");
        preguntasPaciente.setP11("Si");
        preguntasPaciente.setP12("Si");
        preguntasPaciente.setP13("Si");
        preguntasPaciente.setP14("Si");
        preguntasPaciente.setP15("Si");
        preguntasPaciente.setP16("Si");
        preguntasPaciente.setP17("Si");
        preguntasPaciente.setP18("Si");
        preguntasPaciente.setP19("Si");
        preguntasPaciente.setP20("Si");
        preguntasPaciente.setP21("Si");
        preguntasPaciente.setP22("Si");
        preguntasPaciente.setP23("Si");
        preguntasPaciente.setP24("Si");
        preguntasPaciente.setP25("Si");
        preguntasPaciente.setP26("Si");
        preguntasPaciente.setP27("Si");
        preguntasPaciente.setP28("Si");
        preguntasPaciente.setP29("Si");
        preguntasPaciente.setP30("Si");
        preguntasPaciente.setP31("Si");
        preguntasPaciente.setP32("Si");
        preguntasPaciente.setP33("Si");
        examenOrofacial.setCaraForma("Ovoide");
        examenOrofacial.setCaraLabios("Activos");
        examenOrofacial.setBordesBermellon("Visibles");
        atm.setCrepitacion("Si");
        atm.setSensibilidad("Si");
        atm.setDolor("Si");
        atm.setSubluxacion("Si");
        atm.setDesviacion("Si");
        atm.setHabitosTension("Si");
        atm.setTamanoLengua("Normal");
        atm.setTorusMaxilar("Ninguno");
        atm.setTorusMandibular("Ninguno");
        analisisOclusion.setClasificacion("I");
        analisisOclusion.setProteccionCanina("Izquierda");
        analisisOclusion.setProteccionAnterior("Si");
        analisisOclusion.setFuncionGrupo("Izquierda");
        analisisOclusion.setProteccionMutua("Si");
        analisisOclusion.setMordidaCruzada("Izquierda");
        analisisOclusion.setMordidaAbierta("Si");
        analisisOclusion.setSobremordida("Si");
        analisisOclusion.setMandibularProtusivo("Normal");
        analisisOclusion.setMandibularDerecho("Normal");
        analisisOclusion.setMadibularIzquierdo("Normal");
        examenOral.setDolor("Si");
        examenOral.setDolorUbicacion("Difuso");
        examenOral.setDolorEstimulo("Frio");
        examenOral.setLesionPulpar("Frio");
        examenOral.setExposicionPulpar("Caries");
        examenOral.setInflamacion("Extraoral");
        examenOral.setFrioEstado("Normal");
        examenOral.setCalorEstado("Normal");
        hallazgosRadiograficos.setTipoRx("Periapical"); 
        paciente.setSexo("M");
        paciente.setEstadoSalud("Bueno");
        String cadenacontrol = "#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff";
        String dienteval = getControl().getDientes();
        if (dienteval == null){
            getControl().setDientes(cadenacontrol);
        }
        String cadena = "svg/dientes/18.png,svg/dientes/17.png,svg/dientes/16.png,svg/dientes/15.png,svg/dientes/14.png,svg/dientes/13.png,svg/dientes/12.png,svg/dientes/11.png,svg/dientes/21.png,svg/dientes/22.png,svg/dientes/23.png,svg/dientes/24.png,svg/dientes/25.png,svg/dientes/26.png,svg/dientes/27.png,svg/dientes/28.png,svg/dientes/55.png,svg/dientes/54.png,svg/dientes/53.png,svg/dientes/52.png,svg/dientes/51.png,svg/dientes/61.png,svg/dientes/62.png,svg/dientes/63.png,svg/dientes/64.png,svg/dientes/65.png,svg/dientes/85.png,svg/dientes/84.png,svg/dientes/83.png,svg/dientes/82.png,svg/dientes/81.png,svg/dientes/71.png,svg/dientes/72.png,svg/dientes/73.png,svg/dientes/74.png,svg/dientes/75.png,svg/dientes/48.png,svg/dientes/47.png,svg/dientes/46.png,svg/dientes/45.png,svg/dientes/44.png,svg/dientes/43.png,svg/dientes/42.png,svg/dientes/41.png,svg/dientes/31.png,svg/dientes/32.png,svg/dientes/33.png,svg/dientes/34.png,svg/dientes/35.png,svg/dientes/36.png,svg/dientes/37.png,svg/dientes/38.png";
        String dienteperi = getPeriodonto().getDientes();
        if (dienteperi == null){
            getPeriodonto().setDientes(cadena);
        }
    }
    
    public BeanNuevoPaciente() {
        dirreccionPaciente = new DireccionPaciente();
        medico = new MedicoPaciente();
        profe = new String();
        trabajoPaciente = new TrabajoPaciente();
        paciente = new Paciente();
        examenOral = new ExamenOral();
        atm = new Atm();
        analisisOclusion = new AnalisisOclusion();
        hallazgosRadiograficos = new HallazgosRadiograficos();
        examenOrofacial = new ExamenOrofacial();
        preguntasPaciente = new PreguntasPaciente();
        control = new ControlPlaca();
        periodonto = new Periodontograma();
    }
    
    
    
    public void Obtienetodo(){
        String nuevotexto = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nuevotexto");
        getPeriodonto().setDientes(nuevotexto);
    }
    
    
    
    public void guardar() throws SQLException, IOException, JRException{
       // atm.setHabitos_tension(Arrays.toString(habitosTensionList));
        System.out.println("Entra al metodo");
        daoNuevoPaciente daoNuevo = new NuevoPacienteImp();
        boolean inserto = daoNuevo.insertarNuevoPaciente(dirreccionPaciente, medico, trabajoPaciente,  paciente,  control, periodonto, examenOral,  examenOrofacial, atm, analisisOclusion,hallazgosRadiograficos ,preguntasPaciente);
                //examenOrofacial(atm, examenOral, analisisOclusion, hallazgosRadiograficos, examenOrofacial);
        if (inserto) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se ha ingresado satisfactoriamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al guardar datos", "El registro no se puede guardar en este momento. Intente más tarde o contacte a soporte técnico."));
        }
        System.out.println("guarda");
      
    }
    
    public void consentimientoPDF(String paciente,String maestro) throws SQLException, IOException, JRException {
       // int x = 0;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String nombreAlum = (String) session.getAttribute("nombreCompletoAlumno");
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        parametros.put("paciente", paciente);
        parametros.put("maestro", maestro);
        parametros.put("alumno", nombreAlum);
        beanReportes reportes = new beanReportes();
        reportes.exportarPDF("/resources/ReportesPDF/Consentimiento.jasper", "Consentimiento_del_paciente", parametros);
        //return x;
    }
    
    public void obtenerControlPlaca() {
        String nuevotexto = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nuevotexto");
        //System.out.println(nuevotexto);
        getControl().setDientes(nuevotexto);
    }
     
   
    /**
     * @return the dirreccionPaciente
     */
    public DireccionPaciente getDirreccionPaciente() {
        return dirreccionPaciente;
    }

    /**
     * @param dirreccionPaciente the dirreccionPaciente to set
     */
    public void setDirreccionPaciente(DireccionPaciente dirreccionPaciente) {
        this.dirreccionPaciente = dirreccionPaciente;
    }

    /**
     * @return the medico
     */
    public MedicoPaciente getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(MedicoPaciente medico) {
        this.medico = medico;
    }

    /**
     * @return the trabajoPaciente
     */
    public TrabajoPaciente getTrabajoPaciente() {
        return trabajoPaciente;
    }

    /**
     * @param trabajoPaciente the trabajoPaciente to set
     */
    public void setTrabajoPaciente(TrabajoPaciente trabajoPaciente) {
        this.trabajoPaciente = trabajoPaciente;
    }

    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the profe
     */
    public String getProfe() {
        return profe;
    }

    /**
     * @param profe the profe to set
     */
    public void setProfe(String profe) {
        this.profe = profe;
    }

    /**
     * @return the examenOral
     */
    public ExamenOral getExamenOral() {
        return examenOral;
    }

    /**
     * @param examenOral the examenOral to set
     */
    public void setExamenOral(ExamenOral examenOral) {
        this.examenOral = examenOral;
    }


    /**
     * @return the atm
     */
    public Atm getAtm() {
        return atm;
    }

    /**
     * @param atm the atm to set
     */
    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    /**
     * @return the analisisOclusion
     */
    public AnalisisOclusion getAnalisisOclusion() {
        return analisisOclusion;
    }

    /**
     * @param analisisOclusion the analisisOclusion to set
     */
    public void setAnalisisOclusion(AnalisisOclusion analisisOclusion) {
        this.analisisOclusion = analisisOclusion;
    }

    /**
     * @return the hallazgosRadiograficos
     */
    public HallazgosRadiograficos getHallazgosRadiograficos() {
        return hallazgosRadiograficos;
    }

    /**
     * @param hallazgosRadiograficos the hallazgosRadiograficos to set
     */
    public void setHallazgosRadiograficos(HallazgosRadiograficos hallazgosRadiograficos) {
        this.hallazgosRadiograficos = hallazgosRadiograficos;
    }

    /**
     * @return the examenOrofacial
     */
    public ExamenOrofacial getExamenOrofacial() {
        return examenOrofacial;
    }

    /**
     * @param examenOrofacial the examenOrofacial to set
     */
    public void setExamenOrofacial(ExamenOrofacial examenOrofacial) {
        this.examenOrofacial = examenOrofacial;
    }

    /**
     * @return the preguntasPaciente
     */
    public PreguntasPaciente getPreguntasPaciente() {
        return preguntasPaciente;
    }

    /**
     * @param preguntasPaciente the preguntasPaciente to set
     */
    public void setPreguntasPaciente(PreguntasPaciente preguntasPaciente) {
        this.preguntasPaciente = preguntasPaciente;
    }

    /**
     * @return the numdientes
     */
    public String getNumdientes() {
        return numdientes;
    }

    /**
     * @param numdientes the numdientes to set
     */
    public void setNumdientes(String numdientes) {
        this.numdientes = numdientes;
    }

    /**
     * @return the Superficiesplaca
     */
    public String getSuperficiesplaca() {
        return Superficiesplaca;
    }

    /**
     * @param Superficiesplaca the Superficiesplaca to set
     */
    public void setSuperficiesplaca(String Superficiesplaca) {
        this.Superficiesplaca = Superficiesplaca;
    }

    /**
     * @return the numsuperficies
     */
    public String getNumsuperficies() {
        return numsuperficies;
    }

    /**
     * @param numsuperficies the numsuperficies to set
     */
    public void setNumsuperficies(String numsuperficies) {
        this.numsuperficies = numsuperficies;
    }

    /**
     * @return the extensionplaca
     */
    public String getExtensionplaca() {
        return extensionplaca;
    }

    /**
     * @param extensionplaca the extensionplaca to set
     */
    public void setExtensionplaca(String extensionplaca) {
        this.extensionplaca = extensionplaca;
    }

    /**
     * @return the dientecontrol
     */
    public String getDientecontrol() {
        return dientecontrol;
    }

    /**
     * @param dientecontrol the dientecontrol to set
     */
    public void setDientecontrol(String dientecontrol) {
        System.out.println(dientecontrol);
        this.dientecontrol = dientecontrol;
    }

    /**
     * @return the dienteBDcontrol
     */
    public String getDienteBDcontrol() {
        return dienteBDcontrol;
    }

    /**
     * @param dienteBDcontrol the dienteBDcontrol to set
     */
    public void setDienteBDcontrol(String dienteBDcontrol) {
        System.out.println(dienteBDcontrol);
        this.dienteBDcontrol = dienteBDcontrol;
    }

    /**
     * @return the control
     */
    public ControlPlaca getControl() {
        return control;
    }

    /**
     * @param control the control to set
     */
    public void setControl(ControlPlaca control) {
        this.control = control;
    }

    /**
     * @return the dienteimg
     */
    public String getDienteimg() {
        return dienteimg;
    }

    /**
     * @param dienteimg the dienteimg to set
     */
    public void setDienteimg(String dienteimg) {
        System.out.println(dienteimg);
        this.dienteimg = dienteimg;
    }

    /**
     * @return the dienteBDperi
     */
    public String getDienteBDperi() {
        return dienteBDperi;
    }

    /**
     * @param dienteBDperi the dienteBDperi to set
     */
    public void setDienteBDperi(String dienteBDperi) {
        System.out.println(dienteBDperi);
        this.dienteBDperi = dienteBDperi;
    }

    /**
     * @return the periodonto
     */
    public Periodontograma getPeriodonto() {
        return periodonto;
    }

    /**
     * @param periodonto the periodonto to set
     */
    public void setPeriodonto(Periodontograma periodonto) {
        this.periodonto = periodonto;
    }

   
    
    
    public String[] getAlergias() {
        
        return alergias;
    }

    public void setAlergias(String[] alergias) {
        String alergia = Arrays.toString(alergias);
        preguntasPaciente.setAlergiasMedicamentos(alergia);
        System.out.println(alergia);
        this.alergias = alergias;
    }

    
    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }

    public void setSelectedConsoles(String[] selectedConsoles) {
        String padecimiento = Arrays.toString(selectedConsoles);
        preguntasPaciente.setPadecimientos(padecimiento);
        System.out.println(padecimiento);
        this.selectedConsoles = selectedConsoles;
    }
    
    
    public String[] getHabitosTensionList() {
        return habitosTensionList;
    }

    public void setHabitosTensionList(String[] habitosTensionList) {
        String habito = Arrays.toString(habitosTensionList);
        atm.setHabitosTension(habito);
        System.out.println(habito);
        this.habitosTensionList = habitosTensionList;
    }


}