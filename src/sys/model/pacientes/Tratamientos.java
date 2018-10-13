package sys.model.pacientes;
// Generated 17-jul-2018 21:17:55 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Tratamientos generated by hbm2java
 */
public class Tratamientos  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private float precio;
     private String folio;
     private Set<PlanTratamiento> planTratamientos = new HashSet<PlanTratamiento>(0);

    public Tratamientos() {
    }

	
    public Tratamientos(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public Tratamientos(String nombre, float precio, String folio, Set<PlanTratamiento> planTratamientos) {
       this.nombre = nombre;
       this.precio = precio;
       this.folio = folio;
       this.planTratamientos = planTratamientos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public String getFolio() {
        return this.folio;
    }
    
    public void setFolio(String folio) {
        this.folio = folio;
    }
    public Set<PlanTratamiento> getPlanTratamientos() {
        return this.planTratamientos;
    }
    
    public void setPlanTratamientos(Set<PlanTratamiento> planTratamientos) {
        this.planTratamientos = planTratamientos;
    }




}

