/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import sys.model.Alumnos;
import sys.model.ListaAlumnos;
import sys.model.MaestroMaterias;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface daoListaAlumnos {
    
    public List<ListaAlumnos> MostrarAlumnos(MaestroMaterias mmaterias);
    
    public boolean insertarListaAlumnos(ListaAlumnos lista);
    
    public List<ListaAlumnos> MostrarClases(Alumnos alumno, MaestroMaterias mmaterias);
}
