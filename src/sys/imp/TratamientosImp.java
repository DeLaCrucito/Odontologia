/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.daoAreaTratamientos;
import sys.model.AreaTratamientos;
import sys.model.BitacoraRecibos;
import sys.model.MenuTratamientos;
import sys.util.HibernateUtil;

import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TratamientosImp implements daoAreaTratamientos {

    @Override
    public boolean insertarTratamiento(AreaTratamientos tratamiento) {
        boolean inserto=false;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(tratamiento);
            session.getTransaction().commit();
            inserto=true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return inserto;
    }

    @Override
    public boolean insertarMenuTratamientos(MenuTratamientos menu) {
       boolean inserto=false;
        Session session = null;
       try{
           System.out.println("TRAE COMO DATOS"+menu.getAreaTratamientos()+"\n"+menu.getBitacoraReciboses());
           session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.save(menu);
           session.getTransaction().commit();
           inserto=true;
       }catch(Exception e){
           System.out.println(e.getMessage());
           session.getTransaction().rollback();
       }finally{
           if(session!=null){
               session.close();
           }
       }
       return inserto;
    }

    @Override
    public List<AreaTratamientos> mostrarAreaTratamientos() {
        List<AreaTratamientos> mostrarAreaTratamientos=null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql="from AreaTratamientos";
        try{
            mostrarAreaTratamientos = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        }catch(Exception e){
            System.out.print(e.getMessage());
            transaction.rollback();
        }
        
        return mostrarAreaTratamientos;
    }

    @Override
    public List<MenuTratamientos> mostrarMenuTratamientos(BitacoraRecibos bitacora) {
        List<MenuTratamientos> mostrarMenuTratamientos=null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql="from MenuTratamientos where area_tratamiento = "+bitacora.getMenuTratamientos().getAreaTratamientos().getArea();
        try{
            mostrarMenuTratamientos = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        }catch(Exception e){
            System.out.print(e.getMessage());
            transaction.rollback();
        }
        
        return mostrarMenuTratamientos;
    }


}
