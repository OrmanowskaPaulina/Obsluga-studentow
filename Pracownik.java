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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paulinka
 */
public class Pracownik implements PracownikU{
    private String [][]dane = new String[5][100];
    private int p=0, rows=0;
    
    public Pracownik() {
    }
    public void usunS(String imie, String nazwisko) {
        try{
            String line; String []tokenyP = new String[5];
            BufferedReader inP = new BufferedReader(new FileReader("oceny.txt"));
            BufferedWriter Tout = new BufferedWriter(new FileWriter("TEMPoceny.txt", true));
                while ((line = inP.readLine()) != null){
                    tokenyP = line.split(" ");
                    if ((imie).equalsIgnoreCase(tokenyP[0]) && (nazwisko).equalsIgnoreCase(tokenyP[1])){
                         System.out.println(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4]+" usuniety");
                    }
                    else{
                        Tout.write(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4]);
                        Tout.newLine();
                    }
                }
            inP.close();
            Tout.close();
            File file = new File("oceny.txt");
            file.delete();
            File Tfile = new File("TEMPoceny.txt");
            Tfile.renameTo(file);
            Tfile.delete();

            BufferedReader inPP = new BufferedReader(new FileReader("studenci.txt"));
            BufferedWriter ToutP = new BufferedWriter(new FileWriter("TEMPstudenci.txt", true));
            String lineP;
                while ((lineP = inPP.readLine()) != null){
                    tokenyP = lineP.split(" ");
                    if ((imie).equalsIgnoreCase(tokenyP[0]) && (nazwisko).equalsIgnoreCase(tokenyP[1])){
                        System.out.println(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4]+" usuniety");
                    }
                    else{
                        ToutP.write(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4]);
                        ToutP.newLine();
                        System.out.println(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4]+"dodany");
                    }
                }
            inPP.close();
            ToutP.close();
            File fileP = new File("studenci.txt");
            fileP.delete();
            File TfileP = new File("TEMPstudenci.txt");
            TfileP.renameTo(fileP);
            TfileP.delete();
            JOptionPane.showMessageDialog(null, "Usunięto studenta z bazy!", "Sukces", JOptionPane.PLAIN_MESSAGE);
        }
        catch(FileNotFoundException er){
            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
        catch(IOException err){
            JOptionPane.showMessageDialog(null, "Błąd podczas obsługi pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public String[][] bazaS(DefaultTableModel model) {
        try{
            int rowsC = model.getRowCount(); 
            for (int i = rowsC-1; i > 0; i--) {
                model.removeRow(i);
            }
            String line; String []tokeny = new String[5];
            BufferedReader in = new BufferedReader(new FileReader("studenci.txt"));
            while ((line = in.readLine()) != null) {
                tokeny = line.split(" ");
                dane[0][p] = tokeny[0];
                dane[1][p] = tokeny[1];
                dane[2][p] = tokeny[2];
                dane[3][p] = tokeny[3];
                dane[4][p] = tokeny[4];
                p++; 
                model.addRow(new Object[]{tokeny[0], tokeny[1], tokeny[2], tokeny[3], tokeny[4]});
            }  
            in.close();
            rows = p;
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
        try{
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
            BufferedWriter Tout = new BufferedWriter(new FileWriter("TEMPstudenci.txt", true));
            for (int i = 0; i < rows; i++) {
                for (int j=0; j < 5; j++){
                    Tout.write(dane[j][i]);
                    Tout.write(" ");
                }
                Tout.newLine();
            }
            Tout.close();
            File file = new File("studenci.txt");
            file.delete();
            File Tfile = new File("TEMPstudenci.txt");
            Tfile.renameTo(file);
            
        }
        catch(FileNotFoundException er){
            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
        catch(IOException io){
            JOptionPane.showMessageDialog(null, "Błąd w zapisie do pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
    }

    @Override
    public void listaP(DefaultTableModel model) { //album
        try{
            for (int i=0; i < rows; i++){
                for (int j=i+1; j < rows; j++){
                    if((Long.parseLong(dane[2][i])) > (Long.parseLong(dane[2][j]))){
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
            BufferedWriter Tout = new BufferedWriter(new FileWriter("TEMPstudenci.txt", true));
            for (int i = 0; i < rows; i++) {
                for (int j=0; j<5; j++){
                    Tout.write(dane[j][i]);
                    Tout.write(" ");
                }
                Tout.newLine();
            }
            Tout.close();
            File file = new File("studenci.txt");
            file.delete();
            File Tfile = new File("TEMPstudenci.txt");
            Tfile.renameTo(file);
            
        }
        catch(FileNotFoundException er){
            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
        catch(IOException io){
            JOptionPane.showMessageDialog(null, "Błąd w zapisie do pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
}
