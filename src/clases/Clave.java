/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Clave {
    
    public String clave(){
    
       String clave="";
       String [] abc={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
      
       
      
           int numeroAleatorio1=(int)(Math.random()*25+1);
           int numeroAleatorio2=(int)(Math.random()*25+1);
           int numeroAleatorio3=(int)(Math.random()*25+1);
           int numeroAleatorio4=(int)(Math.random()*25+1);
           int numeroAleatorio5=(int)(Math.random()*25+1);
           int numeroAleatorio6=(int)(Math.random()*25+1);
           
           clave=abc[numeroAleatorio1].concat(abc[numeroAleatorio2]).concat(abc[numeroAleatorio3]).concat(abc[numeroAleatorio4]).concat(abc[numeroAleatorio5].concat(abc[numeroAleatorio6]));
           
       
       
       return clave;
        
    }
    
    public static void main(String []args){
        
        Clave c = new Clave();
            System.out.println(c.clave());
    }
}


