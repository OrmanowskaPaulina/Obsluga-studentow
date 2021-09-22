/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazastudentow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paulinka
 */
public class Prowadzacy extends Osoba implements PracownikU{
    protected Przedmiot P;
    private double []oceny = new double[100];
    private String [][]dane = new String[5][100];
    private static int l=0, p=0, rows=0;
    
    public Prowadzacy(Przedmiot P, String imie, String nazwisko) {
        super(imie, nazwisko);
        this.P = P;
    }

    public Prowadzacy() {
    }

    public String getP() {
        return P.nazwa;
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
    
    @Override
    public String[][] bazaS(DefaultTableModel model) {
        try{
            String line; String []tokeny = new String[5];
            BufferedReader in = new BufferedReader(new FileReader("oceny.txt"));
            while ((line = in.readLine()) != null) {
                tokeny = line.split(" ");
                if (this.getP().equalsIgnoreCase(tokeny[2])){
                    dane[0][p] = tokeny[0];
                    dane[1][p] = tokeny[1];
                    dane[2][p] = tokeny[2];
                    dane[3][p] = tokeny[3];
                    dane[4][p] = tokeny[4];
                    p++; 
                    model.addRow(new Object[]{tokeny[0], tokeny[1], tokeny[2], tokeny[3], tokeny[4]});
                }
                rows = p;
            }  
            in.close();
        }
        catch(FileNotFoundException er){
            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
        catch(IOException err){
            JOptionPane.showMessageDialog(null, "Błąd podczas obsługi pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
        }
        return dane;
    }
    
    @Override
    public void listaS(DefaultTableModel model) { //od nazwiska
        //try{
            for (int i=0; i < rows; i++){
                for (int j=i+1; j < rows; j++){
                    if(((dane[1][i].toString()).compareToIgnoreCase(dane[1][j].toString()))>0){
                        System.out.println(dane[1][i].toString()+" "+dane[1][j].toString());
                        String []temp = {dane[0][i], dane[1][i], dane[2][i], dane[3][i], dane[4][i]};
                        dane[0][i] = dane[0][j]; dane[1][i] = dane[1][j]; dane[2][i] = dane[2][j]; dane[3][i] = dane[3][j]; dane[4][i] = dane[4][j];
                        dane[0][j] = temp[0]; dane[1][j] = temp[1]; dane[2][j] = temp[2]; dane[3][j] = temp[3]; dane[4][j] = temp[4];
                        
                        System.out.println(temp[0]+temp[1]+ temp[2]+ temp[3]+ temp[4]);
                        System.out.println(dane[0][i]+dane[1][i]+ dane[2][i]+ dane[3][i]+ dane[4][i]);
                        System.out.println(dane[0][j]+dane[1][j]+ dane[2][j]+ dane[3][j]+ dane[4][j]);
                    } 
                }
            }
            for(int i=0; i<rows; i++){
                    model.addRow(new Object[]{dane[0][i], dane[1][i], dane[2][i], dane[3][i], dane[4][i]});
            }
    }

    @Override
    public void listaP(DefaultTableModel model) { //komentarz
        //try{
            for (int i=0; i < rows; i++){
                for (int j=i+1; j < rows; j++){
                    if(((dane[4][i].toString()).compareToIgnoreCase(dane[4][j].toString()))>0){
                        System.out.println(dane[4][i].toString()+" "+dane[4][j].toString());
                        String []temp = {dane[0][i], dane[1][i], dane[2][i], dane[3][i], dane[4][i]};
                        dane[0][i] = dane[0][j]; dane[1][i] = dane[1][j]; dane[2][i] = dane[2][j]; dane[3][i] = dane[3][j]; dane[4][i] = dane[4][j];
                        dane[0][j] = temp[0]; dane[1][j] = temp[1]; dane[2][j] = temp[2]; dane[3][j] = temp[3]; dane[4][j] = temp[4];
                        
                        System.out.println(temp[0]+temp[1]+ temp[2]+ temp[3]+ temp[4]);
                        System.out.println(dane[0][i]+dane[1][i]+ dane[2][i]+ dane[3][i]+ dane[4][i]);
                        System.out.println(dane[0][j]+dane[1][j]+ dane[2][j]+ dane[3][j]+ dane[4][j]);
                    } 
                }
            }
            for(int i=0; i<rows; i++){
                    model.addRow(new Object[]{dane[0][i], dane[1][i], dane[2][i], dane[3][i], dane[4][i]});
            }
    }
    
    public void usunS(String dane, String imie, String nazwisko, String przedmiot){
        String line; String []tokenyP = new String[5];
        try{
            BufferedReader inP = new BufferedReader(new FileReader("oceny.txt"));
            BufferedWriter Tout = new BufferedWriter(new FileWriter("TEMPoceny.txt", true));
                while ((line = inP.readLine()) != null){
                    tokenyP = line.split(" ");
                    if(imie.equalsIgnoreCase(tokenyP[0]) && nazwisko.equalsIgnoreCase(tokenyP[1]) && przedmiot.equalsIgnoreCase(tokenyP[2])){
                         System.out.println(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4] +" zmiana");
                    }
                    else{
                        Tout.write(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4]);
                        Tout.newLine();
                    }
                }
            String []data = dane.split(" ");
            for (int i=0; i<data.length/3; i++){
                for (int j=0; j+4 <= data.length; j=j+4){
                    System.out.println(data[0]+" "+data[1]+" "+data[2]+" "+data[3]+" "+data[4]+" nowe");
                    System.out.println(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+data[1]+" "+data[2]+" nowe");
                    System.out.println(tokenyP[0]+" "+tokenyP[1]+" "+data[j]+" "+data[j+1]+" "+data[j+2]);
                    Tout.write(tokenyP[0]+" "+tokenyP[1]+" "+data[j]+" "+data[j+1]+" "+data[j+2]);
                }
                Tout.write("\n");
            }
            //System.out.println(data[0]+" "+data[1]+" "+data[2]+" "+data[3]+" "+data[4]+" nowe");
            //System.out.println(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+data[1]+" "+data[2]+" nowe");
            //Tout.write(tokenyP[0]+" "+tokenyP[1]+" "+data[0]+" "+data[1]+" "+data[2]);
            inP.close();
            Tout.close();
            File file = new File("oceny.txt");
            file.delete();
            File Tfile = new File("TEMPoceny.txt");
            Tfile.renameTo(file);
            Tfile.delete();
        } 
        catch(FileNotFoundException er){
            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
        catch(IOException io){
            JOptionPane.showMessageDialog(null, "Błąd w zapisie do pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
        JOptionPane.showMessageDialog(null, "Zaktualizowano oceny!", "Sukces", JOptionPane.PLAIN_MESSAGE);
    }
}
