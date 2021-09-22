/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazastudentow;

import javax.swing.table.DefaultTableModel;

public abstract class Osoba{
    protected String imie, nazwisko;

    public Osoba(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Osoba() {
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
    
    public abstract void nowaOcena(double o);
    
    public abstract double srednia();
    
    public abstract String[][] bazaS(DefaultTableModel model);

    public abstract void listaS(DefaultTableModel model);

    public abstract void listaP(DefaultTableModel model);

    void usunS(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void usunS(String text, String im, String nz, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
