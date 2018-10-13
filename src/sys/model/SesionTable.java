/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author RamsesMtnz
 */
@Entity
@Table(name = "usuarios")
public class SesionTable {

    private int idEmpleado;
    private int tipo;
    private String user;
    private String contraseña;
    
    public SesionTable(){
        
    }

    public SesionTable(int tipo, String usuario,String contraseña, int id){
        this.contraseña=contraseña;
        this.idEmpleado=id;
        this.tipo=tipo;
        this.user=usuario;
    }
    
    public SesionTable(String usuario, String contraseña, int tipo){
        this.user=usuario;
        this.tipo=tipo;
        this.contraseña=contraseña;
    }
    
    /**
     * @return the idEmpleado
     */
    @Id
    @Column(name = "id_empleado")
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the tipo
     */
    @Column(name = "tipo")
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the usuario
     */
       /**
     * @return the contraseña
     */
    @Column(name = "contraseña")
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * @return the user
     */
    @Column(name = "usuario") 
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
    

}
