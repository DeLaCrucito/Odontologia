/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import sys.model.Alumnos;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface daoAlumnos {
    
    public int insertarAlumnos(Alumnos alumnos);
    
    public boolean consultarAlumno(Alumnos login);
    
    public boolean editarPerfil(Alumnos alumno, String newUser, String newPass);
}
