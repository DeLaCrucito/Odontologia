package sys.model;
// Generated 14/10/2017 07:17:00 PM by Hibernate Tools 4.3.1



/**
 * EncargadaClinica generated by hbm2java
 */
public class EncargadaClinica  implements java.io.Serializable {


     private int idEmpleado;
     private String nombre;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private String clinica;
     private String turno;

    public EncargadaClinica() {
    }

	
    public EncargadaClinica(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public EncargadaClinica(int idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String clinica, String turno) {
       this.idEmpleado = idEmpleado;
       this.nombre = nombre;
       this.apellidoPaterno = apellidoPaterno;
       this.apellidoMaterno = apellidoMaterno;
       this.clinica = clinica;
       this.turno = turno;
    }
   
    public int getIdEmpleado() {
        return this.idEmpleado;
    }
    
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getClinica() {
        return this.clinica;
    }
    
    public void setClinica(String clinica) {
        this.clinica = clinica;
    }
    public String getTurno() {
        return this.turno;
    }
    
    public void setTurno(String turno) {
        this.turno = turno;
    }




}


