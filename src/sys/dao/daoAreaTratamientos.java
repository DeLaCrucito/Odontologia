/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import sys.model.AreaTratamientos;
import sys.model.BitacoraRecibos;
import sys.model.MenuTratamientos;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface daoAreaTratamientos {
    
    public boolean insertarTratamiento(AreaTratamientos tratamiento);
     
    public boolean insertarMenuTratamientos(MenuTratamientos menu);
    
    public List<AreaTratamientos> mostrarAreaTratamientos();
    
    public List<MenuTratamientos> mostrarMenuTratamientos(BitacoraRecibos bitacora);
    
    
}
