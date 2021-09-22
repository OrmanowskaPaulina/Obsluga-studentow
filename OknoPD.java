/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazastudentow;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author paulinka
 */
public class OknoPD extends JFrame{

    public OknoPD() throws IOException{
        JFrame frame = new JFrame("PRACOWNIK DZIEKANATU");
        final Pracownik P = new Pracownik();
        JButton nowyS = new JButton("DODAJ NOWEGO STUDENTA"), bazaS = new JButton("PODGLĄD BAZY WSZYSTKICH STUDENTÓW"), 
                student = new JButton("PODGLĄD STUDENTA"), nowyP = new JButton("DODAJ PROWADZĄCEGO"), zamknij = new JButton("ZAMKNIJ");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        panel.add(nowyS); panel.add(bazaS); panel.add(student); panel.add(nowyP); panel.add(new JLabel()); panel.add(zamknij); 
        
        frame.setSize(400, 300);
        JPanel zawartosc = new JPanel(new GridBagLayout());
        frame.setContentPane(zawartosc);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLocationRelativeTo(null);

        nowyS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PRACOWNIK DZIEKANATU");
                JTextField imie = new JTextField(), nazw = new JTextField(), nra = new JTextField(), pesel = new JTextField(), roks = new JTextField();
                JButton nowy = new JButton("DODAJ NOWEGO STUDENTA");

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(7, 2, 10, 10));
                
                panel.add( new JLabel("Imię: ")); panel.add(imie); 
                panel.add( new JLabel("Nazwisko: ")); panel.add(nazw); 
                panel.add( new JLabel("Numer albumu: ")); panel.add(nra); 
                panel.add( new JLabel("Pesel: ")); panel.add(pesel); 
                panel.add( new JLabel("Rok studiów: ") ); panel.add(roks); 
                panel.add( new JLabel()); panel.add(nowy); 
                panel.add( new JLabel()); panel.add(zamknij); 
                
                frame.setSize(500, 300);
                JPanel zawartosc = new JPanel(new GridBagLayout());
                frame.setContentPane(zawartosc);
                frame.add(panel);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                frame.setLocationRelativeTo(null);
                
                zamknij.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose(); 
                    } 
                }); 
                
                nowy.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int pes1=0, alb1=0;
                        String im = imie.getText(), nazwi = nazw.getText(), album = nra.getText(), pes = pesel.getText(), rok = roks.getText();
                        try{
                            BufferedWriter out = new BufferedWriter(new FileWriter("studenci.txt", true));
                            if ( pes.length() != 11 || album.length() != 6 || rok.length() != 1 ){
                                throw new Wyjatek("Sprawdź podane dane liczbowe!");
                            }
                            else{
                                Osoba S = new Student(im, nazwi, album, Long.parseLong(pes), Integer.parseInt(rok));
                                String []data = {im, " ", nazwi, " ", album, " ", pes, " ", rok};
                                for (int i = 0; i < data.length; i++) {
                                    System.out.println(data[i]);
                                    out.write(data[i]);
                                }
                                out.newLine();
                                JOptionPane.showMessageDialog(null, "Dodano nowego studenta!", "Sukces", JOptionPane.PLAIN_MESSAGE);
                            }
                            out.close();
                        }
                        catch(Wyjatek we){
                            JOptionPane.showMessageDialog(null, "Sprawdź podane dane liczbowe!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                        }
                        catch(FileNotFoundException er){
                            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                        }
                        catch(IOException io){
                            JOptionPane.showMessageDialog(null, "Błąd w zapisie do pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                        }
                        catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "Podano dane w błędnym formacie!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                        }
                    } 
                }); 
            } 
        }); 
        
        bazaS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OknoBaza OB = new OknoBaza();
            } 
        }); 
        
        student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame framePD = new JFrame("PRACOWNIK DZIEKANATU");
                JTextField imie = new JTextField(), nazw = new JTextField(), prze = new JTextField(), pes = new JTextField(), alb = new JTextField();
                JButton szukaj = new JButton("SZUKAJ"); JButton wybierz = new JButton("WYBIERZ");
                JLabel imiel = new JLabel("Imię: "), nazwl = new JLabel("Nazwisko: "), pusty = new JLabel(" "),
                        pesell = new JLabel("Numer pesel: "), albuml= new JLabel("Numer albumu: ");
                
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(18, 1, 10, 10));
                
                panel.add( new JLabel("Wyszukiwanie studenta według: ")); 
                String []lista = {" ","Imię i nazwisko","Numer albumu","Numer pesel"};
                JComboBox box = new JComboBox(lista);
                panel.add(box); 
                panel.add(new JLabel()); panel.add(wybierz); 

                framePD.setSize(400, 600);
                JPanel zawartosc = new JPanel(new GridBagLayout());
                framePD.setContentPane(zawartosc);
                framePD.add(panel);
                framePD.setVisible(true);
                framePD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                framePD.setLocationRelativeTo(null);

                wybierz.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String wybor = (String)box.getSelectedItem(); 
                        if (wybor.equals(lista[1])){
                            panel.remove(imie);panel.remove(pesell);panel.remove(albuml);panel.remove(pusty);panel.remove(alb);panel.remove(pes);
                            panel.add(imiel); panel.add(imie);
                            panel.add(nazwl); panel.add(nazw);

                        }
                        else if (wybor.equals(lista[2])){
                            panel.remove(imie);panel.remove(nazw);panel.remove(imiel);panel.remove(nazwl);
                            panel.remove(pesell);panel.remove(pusty);panel.remove(pes);
                            panel.add(albuml); panel.add(alb); 
                        }
                        else if (wybor.equals(lista[3])){
                            panel.remove(imie);panel.remove(nazw);panel.remove(imiel);panel.remove(nazwl);panel.remove(alb);
                            panel.remove(albuml);panel.remove(pusty);
                            panel.add(pesell); panel.add(pes); 
                        }
                        
                        panel.add(pusty); panel.add(szukaj); 
                        panel.add(pusty); panel.add(zamknij);
                        panel.revalidate();
                    }
                }); 
                szukaj.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            double suma=0, srednia=0; int l=0;
                            final String imieSt, nazwSt;
                            Osoba S = new Student();
                            String line, lineO; String []tokeny = new String[5]; String []tokenyO = new String[5];
                            String imieS = imie.getText(), nazwS = nazw.getText(), albumS = alb.getText(), peselS = pes.getText();
                            BufferedReader in = new BufferedReader(new FileReader("studenci.txt"));
                            BufferedReader inO = new BufferedReader(new FileReader("oceny.txt"));
                            JRadioButton r1 = new JRadioButton("TAK"), r2 = new JRadioButton("NIE");     
                            JButton potwierdz = new JButton("POTWIERDŹ");
                            ButtonGroup bg = new ButtonGroup();    
                            bg.add(r1); bg.add(r2);    
                            while ((line = in.readLine()) != null) {
                                tokeny = line.split(" ");
                                System.out.println(line+ tokeny[0]+ tokeny[1]+ tokeny[2]+ tokeny[3]+ tokeny[4]);
                                if (tokeny[0].equalsIgnoreCase(imieS) && tokeny[1].equalsIgnoreCase(nazwS)){
                                    S = new Student(tokeny[0], tokeny[1], tokeny[2], Long.parseLong(tokeny[3]), Integer.valueOf(tokeny[4]));
                                    while ((lineO = inO.readLine()) != null){
                                        tokenyO = lineO.split(" ");
                                        if (tokenyO[0].equalsIgnoreCase(tokeny[0]) && (tokenyO[1].equalsIgnoreCase(tokeny[1]))){
                                            S.nowaOcena(Double.parseDouble(tokenyO[3]));
                                        }   
                                    }
                                    panel.add(new JLabel("Wpisać zaliczenie roku dla studenta: "));
                                    panel.add(new JLabel(S.getImie()+" "+S.getNazwisko()));
                                    panel.add(new JLabel("o średniej " +Double.toString(S.srednia())));
                                    panel.add(r1); panel.add(r2); 
                                    panel.add(potwierdz);
                                    panel.revalidate();
                                }
                                else if(tokeny[2].equalsIgnoreCase(albumS)){
                                    S = new Student(tokeny[0], tokeny[1], tokeny[2], Long.parseLong(tokeny[3]), Integer.valueOf(tokeny[4]));
                                    while ((lineO = inO.readLine()) != null){
                                        tokenyO = lineO.split(" ");
                                        if (tokenyO[0].equalsIgnoreCase(tokeny[0]) && (tokenyO[1].equalsIgnoreCase(tokeny[1]))){
                                            S.nowaOcena(Double.parseDouble(tokenyO[3]));
                                        }   
                                    }
                                    panel.add(new JLabel("Wpisać zaliczenie roku dla studenta: "));
                                    panel.add(new JLabel(S.getImie()+" "+S.getNazwisko()));
                                    panel.add(new JLabel("o średniej " +Double.toString(S.srednia())));
                                    panel.add(r1); panel.add(r2); 
                                    panel.add(potwierdz);
                                    panel.revalidate();
                                }
                                else if(tokeny[3].equalsIgnoreCase(peselS)){
                                    S = new Student(tokeny[0], tokeny[1], tokeny[2], Long.parseLong(tokeny[3]), Integer.valueOf(tokeny[4]));
                                    while ((lineO = inO.readLine()) != null){
                                        tokenyO = lineO.split(" ");
                                        if (tokenyO[0].equalsIgnoreCase(tokeny[0]) && (tokenyO[1].equalsIgnoreCase(tokeny[1]))){
                                            S = new Student(tokeny[0], tokeny[1], tokeny[2], Long.parseLong(tokeny[3]), Integer.valueOf(tokeny[4]));
                                            S.nowaOcena(Double.parseDouble(tokenyO[3]));
                                        }   
                                    }
                                    panel.add(new JLabel("Wpisać zaliczenie roku dla studenta: "));
                                    panel.add(new JLabel(S.getImie()+" "+S.getNazwisko()));
                                    panel.add(new JLabel("o średniej " +Double.toString(S.srednia())));
                                    panel.add(r1); panel.add(r2); 
                                    panel.add(potwierdz);
                                    panel.revalidate();
                                }

                            }
                            in.close();
                            inO.close();
                            imieSt = S.getImie(); nazwSt = S.getNazwisko();
                            potwierdz.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    try{
                                    String lineP; String []tokenyP = new String[5];
                                    BufferedReader inP = new BufferedReader(new FileReader("studenci.txt"));
                                    BufferedWriter Tout = new BufferedWriter(new FileWriter("TEMPstudenci.txt", true));
                                    if (r1.isSelected()){
                                            while ((lineP = inP.readLine()) != null){
                                                tokenyP = lineP.split(" ");
                                                System.out.println(imieSt+" "+nazwSt);
                                                System.out.println(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4]);
                                                if ((imieSt).equalsIgnoreCase(tokenyP[0]) && (nazwSt).equalsIgnoreCase(tokenyP[1])){
                                                    System.out.println("eq");
                                                    int ocena = Integer.parseInt(tokenyP[4]);
                                                    ocena += 1;
                                                    tokenyP[4] = String.valueOf(ocena);
                                                    JOptionPane.showMessageDialog(null, "Zapisano studenta na kolejny rok!", "Sukces", JOptionPane.PLAIN_MESSAGE);
                                                }
                                                Tout.write(tokenyP[0]+" "+tokenyP[1]+" "+tokenyP[2]+" "+tokenyP[3]+" "+tokenyP[4]);
                                                Tout.newLine();
                                            }
                                        Tout.close();
                                        inP.close();
                                        File file = new File("studenci.txt");
                                        file.delete();
                                        File Tfile = new File("TEMPstudenci.txt");
                                        Tfile.renameTo(file);
                                        }
                                    else if(r2.isSelected()){
                                        P.usunS(imieSt, nazwSt);
                                    }
                                        
                                    } 
                                    catch (IOException ex) {
                                        Logger.getLogger(OknoPD.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        }
                                    });
                            }
                        catch(FileNotFoundException er){
                            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                        }
                        catch(IOException io){
                            JOptionPane.showMessageDialog(null, "Błąd w zapisie do pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                        }
                    }
                }); 
                
                zamknij.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        framePD.dispose(); 
                    } 
                }); 
            } 
            
        }); 
        
        nowyP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame framePD = new JFrame("PRACOWNIK DZIEKANATU");
                JTextField imie = new JTextField(), nazw = new JTextField(), prze = new JTextField();
                JButton nowy = new JButton("DODAJ PROWADZĄCEGO");

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(7, 2, 10, 10));
                
                panel.add(new JLabel("Imię: ")); panel.add(imie); 
                panel.add(new JLabel("Nazwisko: ")); panel.add(nazw); 
                panel.add(new JLabel("Przedmiot: ")); panel.add(prze); 
                panel.add(new JLabel()); panel.add(nowy); 
                panel.add(new JLabel()); panel.add(zamknij); 
                
                framePD.setSize(500, 300);
                JPanel zawartosc = new JPanel(new GridBagLayout());
                framePD.setContentPane(zawartosc);
                framePD.add(panel);
                framePD.setVisible(true);
                framePD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                framePD.setLocationRelativeTo(null);
                
                zamknij.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        framePD.dispose(); 
                    } 
                }); 
                
                nowy.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String im = imie.getText(), nazwi = nazw.getText(), przedmiot = prze.getText();
                        try{
                            Przedmiot P = new Przedmiot(przedmiot);
                            Osoba Pr = new Prowadzacy(P, im, nazwi);
                            BufferedWriter out = new BufferedWriter(new FileWriter("pracownicy.txt", true));
                            String []data = {im, " ", nazwi, " ", przedmiot};
                            for (int i = 0; i < data.length; i++) {
                                System.out.println(data[i]);
                                out.write(data[i]);
                            }
                            out.newLine();
                            JOptionPane.showMessageDialog(null, "Dodano prowadzącego:\n" + im+" "+nazwi, "Sukces", JOptionPane.PLAIN_MESSAGE);
                            out.close();
                       }
                       catch(FileNotFoundException er){
                           JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                       }
                       catch(IOException io){
                           JOptionPane.showMessageDialog(null, "Błąd w zapisie do pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                       }
                    } 
                }); 
                
            } 
        }); 
        zamknij.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
            } 
        }); 
    }
    
}
