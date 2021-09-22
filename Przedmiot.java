/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazastudentow;

public class Przedmiot{
    protected String nazwa;
    private double []oceny = new double[100];
    private static int l=0;
    
    public Przedmiot(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }
    
    public void nowaOcena(double o){
        oceny[l] = o;
        l++;
    }
    
    public double srednia(){
        double srednia, suma=0;
        for (int i=0; i<l; i++){
            suma += oceny[i];
        }
        srednia = suma/l;
        return srednia;
    }
}
