package sys.model;
// Generated 14/10/2017 07:17:00 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Alumnos generated by hbm2java
 */
public class Alumnos  implements java.io.Serializable {


 private int matricula;
     private String nombre;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private String semestre;
     private String grupo;
     private String usuario;
     private String contraseña;
     private Set<ListaAlumnos> listaAlumnoses = new HashSet<ListaAlumnos>(0);
     private Set<BitacoraRecibos> bitacoraReciboses = new HashSet<BitacoraRecibos>(0);

    public Alumnos() {
    }

    public Alumnos(int matricula, String nombre, String apellidoPaterno, String apellidoMaterno, String usuario, String contraseña,String semestre,String grupo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.semestre=semestre;
        this.grupo=grupo;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
	
    public Alumnos(int matricula, String nombre, String apellidoPaterno, String apellidoMaterno, String usuario, String contraseña) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    public Alumnos(int matricula, String nombre, String apellidoPaterno, String apellidoMaterno, String semestre, String grupo, String usuario, String contraseña, Set<ListaAlumnos> listaAlumnoses, Set<BitacoraRecibos> bitacoraReciboses) {
       this.matricula = matricula;
       this.nombre = nombre;
       this.apellidoPaterno = apellidoPaterno;
       this.apellidoMaterno = apellidoMaterno;
       this.semestre = semestre;
       this.grupo = grupo;
       this.usuario = usuario;
       this.contraseña = contraseña;
       this.listaAlumnoses = listaAlumnoses;
       this.bitacoraReciboses = bitacoraReciboses;
    }
   
    public int getMatricula() {
        return this.matricula;
    }
    
    public void setMatricula(int matricula) {
        this.matricula = matricula;
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
    public String getSemestre() {
        return this.semestre;
    }
    
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    public String getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContraseña() {
        return this.contraseña;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public Set<ListaAlumnos> getListaAlumnoses() {
        return this.listaAlumnoses;
    }
    
    public void setListaAlumnoses(Set<ListaAlumnos> listaAlumnoses) {
        this.listaAlumnoses = listaAlumnoses;
    }
    public Set<BitacoraRecibos> getBitacoraReciboses() {
        return this.bitacoraReciboses;
    }
    
    public void setBitacoraReciboses(Set<BitacoraRecibos> bitacoraReciboses) {
        this.bitacoraReciboses = bitacoraReciboses;
    }



}


