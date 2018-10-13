/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import sys.model.Maestro;
import sys.model.MaestroMaterias;
import sys.model.Materias;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface daoMaestro {

    public boolean insertarMaestro(Maestro maestro);

    public List<Maestro> mostrarMaestro();

    public boolean editarMaestro(Maestro maestro);

    public boolean eliminarMaestro(Maestro maestro);

    public List<Materias> mostrarMaterias(MaestroMaterias maestroM);

    public boolean insertarMaestroMaterias(MaestroMaterias maestroM);

    public String mostrarCodigo();

    public boolean eliminarMateriaMaestro(MaestroMaterias mmaterias);

}
