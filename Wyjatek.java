/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazastudentow;

/**
 *
 * @author paulinka
 */
public class Wyjatek extends Exception{
    
    public Wyjatek(){
    }
    
    public Wyjatek(String komunikat){
        super(komunikat);
    }
}
