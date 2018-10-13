/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import sys.model.BitacoraRecibos;
import sys.model.MaestroMaterias;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface daoBitacora {
    
    public boolean insertarTratamiento(BitacoraRecibos bitacora);
    
    public List<BitacoraRecibos> mostratTratamientosPorAlumno(MaestroMaterias mmaterias);
    
    public List<BitacoraRecibos> mostrarTratamientosPorMaestros(MaestroMaterias mmaterias);
    
    public List<BitacoraRecibos> mostrarTratamientosPorDia(int mes);
      
}
