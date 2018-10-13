/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import sys.model.EncargadaClinica;
import sys.model.Maestro;
import sys.model.SesionTable;

/**
 *
 * @author RamsesMtnz
 */
public interface daoLoginSesion {
    
    public int consultarUsuario(SesionTable login);
    
    //public boolean consultarAlumno(Alumnos login);
    
    public int editarPerfil(SesionTable user, String userOld, String passOld);
    
    public int consultarDocente(Maestro docente, SesionTable user);
    
    public int consultarFarmacia(EncargadaClinica encargada, SesionTable user);
    
}
