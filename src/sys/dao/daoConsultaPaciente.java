/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import sys.model.pacientes.Consultas;
import sys.model.pacientes.ControlPlaca;
import sys.model.pacientes.Paciente;
import sys.model.pacientes.Periodontograma;

import java.util.List;

/**
 *
 * @author Ramses Mtnz Santiago
 */
public interface daoConsultaPaciente {
    
    public List<Paciente> verFoliosPaciente(Paciente paciente);
    
    public List<Paciente> verNombresPaciente(Paciente paciente);
    
    public boolean insertaConsulta(ControlPlaca control, Periodontograma periodonto, Paciente paciente);
    
    public List <Consultas> verConsultas(int paciente);
    
    public String verPlaca(ControlPlaca control);
    
    public String verPeriodontograma(Periodontograma periodontograma);
    
    public boolean eliminarExpediente(int consultas);

    public boolean eliminarPacientes(int paciente);
}
