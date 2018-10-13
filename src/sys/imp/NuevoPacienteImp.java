/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import sys.dao.daoNuevoPaciente;
import sys.model.pacientes.*;
import sys.util.HibernateUtil;

import java.util.Date;

/**
 *
 * @author Ramses Mtnz Santiago
 */
public class NuevoPacienteImp implements daoNuevoPaciente {

    @Override
    public boolean insertarNuevoPaciente(DireccionPaciente direccion,MedicoPaciente medico,TrabajoPaciente trabajo, Paciente paciente, ControlPlaca control,Periodontograma periodonto,ExamenOral examenoral, ExamenOrofacial examen,Atm atm,AnalisisOclusion analisis,HallazgosRadiograficos hallazgos,PreguntasPaciente preguntas) {
        boolean inserto = false;
        Session session = null;
        try {
            //primera pestaña del paciente
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(direccion);
            session.save(medico);
            session.save(trabajo);
            paciente.setDireccionPaciente(direccion);
            paciente.setMedicoPaciente(medico);
            paciente.setTrabajoPaciente(trabajo);
            paciente.setFechaRegistro(new Date());
            session.save(paciente);
            
            //segunda pestaña del cuestionario
            preguntas.setAlergiasMedicamentos(preguntas.getAlergiasMedicamentos());
            preguntas.setPadecimientos(preguntas.getPadecimientos());
            preguntas.setPaciente(paciente);
            session.save(preguntas);
            
            //tercera pestaña de examenes oral y orofacial
            session.save(examenoral);
            session.save(atm);
            session.save(analisis);
            session.save(hallazgos);
            examen.setAtm(atm);
            examen.setAnalisisOclusion(analisis);
            examen.setHallazgosRadiograficos(hallazgos);
            examen.setExamenOral(examenoral);
            examen.setPaciente(paciente);
            examen.setFecha(new Date());
            session.save(examen);
            
            //insertar la placa dentobacteriana
            control.setEstado("nuevo");
            System.out.println(control.getDientes());
            control.setDientes(control.getDientes());
            control.setPaciente(paciente);
            session.save(control);
            
            
            //insertar tabla odontograma
            periodonto.setEstado("nuevo");
            periodonto.setPaciente(paciente);
            periodonto.setDientes(periodonto.getDientes());
            session.save(periodonto);
            
            Consultas primera = new Consultas();
            primera.setFolio(paciente.getFolio());
            primera.setPaciente(paciente);
            primera.setControlPlaca(control);
            primera.setPeriodontograma(periodonto);
            primera.setFecha(new Date());
            session.save(primera);
            
            session.getTransaction().commit();
            inserto = true;
            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return inserto;
    
    
    }


}
