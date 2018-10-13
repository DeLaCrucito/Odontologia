package sys.model.pacientes;
// Generated 17-jul-2018 21:17:55 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * AnalisisOclusion generated by hbm2java
 */
public class AnalisisOclusion  implements java.io.Serializable {


     private Integer id;
     private String clasificacion;
     private String proteccionCanina;
     private String proteccionAnterior;
     private String funcionGrupo;
     private String proteccionMutua;
     private String mordidaCruzada;
     private String mordidaAbierta;
     private String sobremordida;
     private String contactoDentatorio;
     private String incisalHorizontal;
     private String incisalVertical;
     private String mandibularProtusivo;
     private String mandibularDerecho;
     private String madibularIzquierdo;
     private Set<ExamenOrofacial> examenOrofacials = new HashSet<ExamenOrofacial>(0);

    public AnalisisOclusion() {
    }

	
    public AnalisisOclusion(String clasificacion, String proteccionCanina, String proteccionAnterior, String funcionGrupo, String proteccionMutua, String mordidaCruzada, String mordidaAbierta, String sobremordida, String contactoDentatorio) {
        this.clasificacion = clasificacion;
        this.proteccionCanina = proteccionCanina;
        this.proteccionAnterior = proteccionAnterior;
        this.funcionGrupo = funcionGrupo;
        this.proteccionMutua = proteccionMutua;
        this.mordidaCruzada = mordidaCruzada;
        this.mordidaAbierta = mordidaAbierta;
        this.sobremordida = sobremordida;
        this.contactoDentatorio = contactoDentatorio;
    }
    public AnalisisOclusion(String clasificacion, String proteccionCanina, String proteccionAnterior, String funcionGrupo, String proteccionMutua, String mordidaCruzada, String mordidaAbierta, String sobremordida, String contactoDentatorio, String incisalHorizontal, String incisalVertical, String mandibularProtusivo, String mandibularDerecho, String madibularIzquierdo, Set<ExamenOrofacial> examenOrofacials) {
       this.clasificacion = clasificacion;
       this.proteccionCanina = proteccionCanina;
       this.proteccionAnterior = proteccionAnterior;
       this.funcionGrupo = funcionGrupo;
       this.proteccionMutua = proteccionMutua;
       this.mordidaCruzada = mordidaCruzada;
       this.mordidaAbierta = mordidaAbierta;
       this.sobremordida = sobremordida;
       this.contactoDentatorio = contactoDentatorio;
       this.incisalHorizontal = incisalHorizontal;
       this.incisalVertical = incisalVertical;
       this.mandibularProtusivo = mandibularProtusivo;
       this.mandibularDerecho = mandibularDerecho;
       this.madibularIzquierdo = madibularIzquierdo;
       this.examenOrofacials = examenOrofacials;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getClasificacion() {
        return this.clasificacion;
    }
    
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    public String getProteccionCanina() {
        return this.proteccionCanina;
    }
    
    public void setProteccionCanina(String proteccionCanina) {
        this.proteccionCanina = proteccionCanina;
    }
    public String getProteccionAnterior() {
        return this.proteccionAnterior;
    }
    
    public void setProteccionAnterior(String proteccionAnterior) {
        this.proteccionAnterior = proteccionAnterior;
    }
    public String getFuncionGrupo() {
        return this.funcionGrupo;
    }
    
    public void setFuncionGrupo(String funcionGrupo) {
        this.funcionGrupo = funcionGrupo;
    }
    public String getProteccionMutua() {
        return this.proteccionMutua;
    }
    
    public void setProteccionMutua(String proteccionMutua) {
        this.proteccionMutua = proteccionMutua;
    }
    public String getMordidaCruzada() {
        return this.mordidaCruzada;
    }
    
    public void setMordidaCruzada(String mordidaCruzada) {
        this.mordidaCruzada = mordidaCruzada;
    }
    public String getMordidaAbierta() {
        return this.mordidaAbierta;
    }
    
    public void setMordidaAbierta(String mordidaAbierta) {
        this.mordidaAbierta = mordidaAbierta;
    }
    public String getSobremordida() {
        return this.sobremordida;
    }
    
    public void setSobremordida(String sobremordida) {
        this.sobremordida = sobremordida;
    }
    public String getContactoDentatorio() {
        return this.contactoDentatorio;
    }
    
    public void setContactoDentatorio(String contactoDentatorio) {
        this.contactoDentatorio = contactoDentatorio;
    }
    public String getIncisalHorizontal() {
        return this.incisalHorizontal;
    }
    
    public void setIncisalHorizontal(String incisalHorizontal) {
        this.incisalHorizontal = incisalHorizontal;
    }
    public String getIncisalVertical() {
        return this.incisalVertical;
    }
    
    public void setIncisalVertical(String incisalVertical) {
        this.incisalVertical = incisalVertical;
    }
    public String getMandibularProtusivo() {
        return this.mandibularProtusivo;
    }
    
    public void setMandibularProtusivo(String mandibularProtusivo) {
        this.mandibularProtusivo = mandibularProtusivo;
    }
    public String getMandibularDerecho() {
        return this.mandibularDerecho;
    }
    
    public void setMandibularDerecho(String mandibularDerecho) {
        this.mandibularDerecho = mandibularDerecho;
    }
    public String getMadibularIzquierdo() {
        return this.madibularIzquierdo;
    }
    
    public void setMadibularIzquierdo(String madibularIzquierdo) {
        this.madibularIzquierdo = madibularIzquierdo;
    }
    public Set<ExamenOrofacial> getExamenOrofacials() {
        return this.examenOrofacials;
    }
    
    public void setExamenOrofacials(Set<ExamenOrofacial> examenOrofacials) {
        this.examenOrofacials = examenOrofacials;
    }




}

