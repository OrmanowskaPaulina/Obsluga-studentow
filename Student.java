/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazastudentow;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paulinka
 */
public class Student extends Osoba {
    protected String nralbumu;
    protected int rokstudiow; long pesel;
    private double []oceny = new double[50];
    private static int k=0;

    public Student(String imie, String nazwisko, String nralbumu, long pesel, int rokstudiow) {
        super(imie, nazwisko);
        this.nralbumu = nralbumu;
        this.pesel = pesel;
        this.rokstudiow = rokstudiow;
    }
    
    public Student(){
        
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
    
    public void nowaOcena(double o){
        oceny[k] = o;
        k++;
    }
    
    public double srednia(){
        double srednia, suma=0;
        for (int i = 0; i<k; i++){
            suma += oceny[i];
        }
        srednia = suma/k;
        return srednia;
    }

    @Override
    public String[][] bazaS(DefaultTableModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listaS(DefaultTableModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listaP(DefaultTableModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
