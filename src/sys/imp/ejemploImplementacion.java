/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import sys.dao.ejemploInterface;
import sys.model.SesionTable;

/**
 *
 * @author Ramses Mtnz Santiago
 */
public class ejemploImplementacion implements ejemploInterface {

    @Override
    public boolean ejemplo(SesionTable usuario) {
        //realizas la consulta o metodo de insercion
        return true;
    }
    
}
