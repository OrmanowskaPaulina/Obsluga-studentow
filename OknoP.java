/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazastudentow;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author paulinka
 */
public class OknoP{ //prowadzący zajecia
    
    public OknoP() {
        String przedmiotP, oceny, imieP, nazwiskoP;
        JFrame frameS = new JFrame("PROWADZĄCY ZAJĘCIA");
        JTextField im = new JTextField(), nazw = new JTextField(), prze = new JTextField();
        JButton zatwierdz = new JButton("ZATWIERDŹ DANE"), zamknij = new JButton("ZAMKNIJ");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        panel.add( new JLabel("Imię: ") ); 
        panel.add(im); 
        panel.add( new JLabel("Nazwisko: ") ); 
        panel.add(nazw); 
        panel.add( new JLabel("Przedmiot: ") ); 
        panel.add(prze); 
        panel.add(new JLabel()); panel.add(zatwierdz); panel.add(new JLabel()); panel.add(zamknij); 
        
        frameS.setSize(400, 300);
        JPanel zawartosc = new JPanel(new GridBagLayout());
        frameS.setContentPane(zawartosc);
        frameS.add(panel);
        frameS.setVisible(true);
        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frameS.setLocationRelativeTo(null);
        
        try{
            imieP =  im.getText(); nazwiskoP =  nazw.getText(); przedmiotP =  prze.getText(); 
            String line; String []tokeny = new String[5];
            BufferedReader in = new BufferedReader(new FileReader("pracownicy.txt"));
            while ((line = in.readLine()) != null) {
                tokeny = line.split(" ");
                System.out.println(imieP+nazwiskoP+przedmiotP);
                if (imieP.equalsIgnoreCase(tokeny[0]) && nazwiskoP.equalsIgnoreCase(tokeny[1]) && przedmiotP.equalsIgnoreCase(tokeny[1])){
                    System.out.println(imieP+nazwiskoP+przedmiotP);
                    JOptionPane.showMessageDialog(null, "Zalogowano prowadzacego "+przedmiotP+" !", "SUKCES", JOptionPane.PLAIN_MESSAGE);
                }
                else{

                }
            }   
            in.close();
        }
        catch(FileNotFoundException er){
            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
        }
        catch(IOException err){
            JOptionPane.showMessageDialog(null, "Błąd podczas obsługi pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
        } 
        
        zatwierdz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PROWADZĄCY ZAJĘCIA");
                JButton bazaS = new JButton("PODGLĄD BAZY WSZYSTKICH STUDENTÓW"), student = new JButton("WYBRANY STUDENT"), 
                        srednia = new JButton("ŚREDNIA Z PRZEDMIOTU"), zamknij = new JButton("ZAMKNIJ");

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(7, 1, 10, 10));

                panel.add(bazaS); panel.add(student); panel.add(srednia); panel.add(new JLabel()); panel.add(zamknij); 

                frame.setSize(400, 300);
                JPanel zawartosc = new JPanel(new GridBagLayout());
                frame.setContentPane(zawartosc);
                frame.add(panel);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                frame.setLocationRelativeTo(null); 
                
                bazaS.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String imiePr =  im.getText(), nazwiskoPr =  nazw.getText(),  przedmiotPr =  prze.getText(); 
                        OknoBaza Ob = new OknoBaza(imiePr, nazwiskoPr, przedmiotPr);
                    } 
                }); 
                zamknij.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frameS.dispose(); 
                    } 
                }); 
                student.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame frameS = new JFrame("PROWADZĄCY ZAJĘCIA");
                        JTextField imie = new JTextField(), nazw = new JTextField();
                        JButton dodaj = new JButton("NOWA OCENA"), 
                                edytuj = new JButton("EDYTUJ OCENY"), zamknij = new JButton("ZAMKNIJ");

                        panel.add(new JLabel());JPanel panel = new JPanel();
                        panel.setLayout(new GridLayout(5, 2, 10, 10));

                        panel.add( new JLabel("Imię: ") ); 
                        panel.add(imie); 

                        panel.add( new JLabel("Nazwisko: ") ); 
                        panel.add(nazw); panel.add(new JLabel()); panel.add(dodaj); panel.add(new JLabel()); panel.add(edytuj); 
                        panel.add(new JLabel()); panel.add(zamknij); 

                        frameS.setSize(400, 300);
                        JPanel zawartosc = new JPanel(new GridBagLayout());
                        frameS.setContentPane(zawartosc);
                        frameS.add(panel);
                        frameS.setVisible(true);
                        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                        frameS.setLocationRelativeTo(null);
                        
                        zamknij.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                frameS.dispose(); 
                            } 
                        }); 
                        
                        edytuj.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame frameS = new JFrame("EDYCJA DANYCH");
                                JButton zatwierdz = new JButton("ZATWIERDŹ DANE"), zamknij = new JButton("ZAMKNIJ");
                                Osoba P = new Prowadzacy(); String im = imie.getText(), nz = nazw.getText();
                                
                                JPanel panel = new JPanel(), panel2 = new JPanel();
                                panel.setLayout(new FlowLayout()); 

                                JTextArea textArea = new JTextArea(20, 30);
                                textArea.setLineWrap(true);
                                textArea.setWrapStyleWord(true);
                                JScrollPane scrollPane = new JScrollPane(textArea);
                                panel.add(scrollPane); panel.add(zatwierdz); panel.add(zamknij); 
                                frameS.setSize(450, 400);
                                frameS.add(panel); 
                                frameS.setVisible(true);
                                frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                                frameS.setLocationRelativeTo(null);
                                zamknij.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        frameS.dispose(); 
                                    } 
                                }); 
                                zatwierdz.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        P.usunS(textArea.getText(), im, nz, prze.getText());
                                    } 
                                }); 
                            try{
                                String line; String []tokeny = new String[5];
                                BufferedReader in = new BufferedReader(new FileReader("oceny.txt"));
                                while ((line = in.readLine()) != null) {
                                    tokeny = line.split(" ");
                                    if (im.equalsIgnoreCase(tokeny[0]) && nz.equalsIgnoreCase(tokeny[1]) && (prze.getText()).equalsIgnoreCase(tokeny[2])){
                                        //String appendText = "Przedmiot: " + tokeny[2] + " Ocena: " + tokeny[3]+" Komentarz: "+tokeny[4]+"\n";
                                        String appendText =  tokeny[2] + " " + tokeny[3] + " " + tokeny[4] + "\n";
                                        textArea.append(appendText);
                                    }
                                }   
                                in.close();
                            }
                            catch(FileNotFoundException er){
                                JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                            }
                            catch(IOException err){
                                JOptionPane.showMessageDialog(null, "Błąd podczas obsługi pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
                            } 
                        } 
                        }); 
                        zamknij.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                frameS.dispose(); 
                            } 
                        }); 
                        dodaj.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                String im = imie.getText(), nz = nazw.getText(), przedmiotP = prze.getText();
                                JFrame frameS = new JFrame("PROWADZĄCY ZAJĘCIA");
                                JTextField ocena = new JTextField(), kom = new JTextField();
                                JButton zatwierdz = new JButton("ZATWIERDŹ"), zamknij = new JButton("ZAMKNIJ");

                                JPanel panel = new JPanel();
                                panel.setLayout(new GridLayout(8, 1, 10, 10));

                                panel.add( new JLabel("Nowa ocena dla studenta: \n"+im+" "+nz) ); 

                                panel.add( new JLabel("Ocena: ") ); 
                                panel.add(ocena); 
                                panel.add( new JLabel("Komentarz: ") ); 
                                panel.add(kom); 

                                panel.add(zatwierdz); panel.add(new JLabel()); panel.add(zamknij); 

                                frameS.setSize(400, 400);
                                JPanel zawartosc = new JPanel(new GridBagLayout());
                                frameS.setContentPane(zawartosc);
                                frameS.add(panel);
                                frameS.setVisible(true);
                                frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                                frameS.setLocationRelativeTo(null);
                                
                                zatwierdz.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try{
                                            String oceny = ocena.getText(), komentarz = kom.getText(), line; String []tokeny = new String[5];
                                            BufferedWriter out = new BufferedWriter(new FileWriter("oceny.txt", true));
                                            if (oceny.length()>4){
                                                throw new Wyjatek("Sprawdź podane dane liczbowe!");
                                            }
                                            if (Double.parseDouble(oceny) < 2){
                                                throw new Wyjatek0("Sprawdź podane dane liczbowe!");
                                            }
                                            BufferedReader in = new BufferedReader(new FileReader("studenci.txt"));
                                            String []data = {im, " ", nz, " ", przedmiotP, " ", oceny, " ", komentarz};
                                            while ((line = in.readLine()) != null) {
                                                tokeny = line.split(" ");
                                                if (tokeny[0].equalsIgnoreCase(im) && tokeny[1].equalsIgnoreCase(nz)){
                                                    out.write("\n");
                                                    for (int i = 0; i < data.length; i++) {
                                                    System.out.println(data[i]);
                                                    out.write(data[i]);
                                                    }
                                                //out.newLine();
                                                }
                                            }   
                                            JOptionPane.showMessageDialog(null, "Dodano ocenę!" , "Sukces", JOptionPane.PLAIN_MESSAGE);
                                            in.close(); out.close();
                                        }
                                        catch(Wyjatek0 we0){
                                            JOptionPane.showMessageDialog(null, "Minimalna ocena to 2!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                                        }
                                        catch(Wyjatek we){
                                            JOptionPane.showMessageDialog(null, "Sprawdź podane dane liczbowe!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                                        }
                                        catch(FileNotFoundException er){
                                            JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                                        }
                                        catch(IOException err){
                                            JOptionPane.showMessageDialog(null, "Błąd podczas obsługi pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
                                        } 
                                    } 
                                }); 
                            zamknij.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                frameS.dispose(); 
                            } 
                        }); 
                    } 
                }); 
                
                zamknij.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frameS.dispose(); 
                    } 
                }); 
            }
        });
        srednia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //final JLabel srednial, wartoscl;
                Osoba P = new Prowadzacy();
                double suma = 0;
                int l = 0;
                try{
                    String line; String []tokeny = new String[5];
                    String przedmiotS = prze.getText(), imS = im.getText(), nazwS = nazw.getText();
                    Przedmiot Ps = new Przedmiot(przedmiotS);
                    P = new Prowadzacy(Ps, imS, nazwS);
                    BufferedReader in = new BufferedReader(new FileReader("oceny.txt"));
                    while ((line = in.readLine()) != null) {
                        tokeny = line.split(" ");
                        if (tokeny[2].equalsIgnoreCase(Ps.getNazwa())){
                            Ps.nowaOcena(Double.parseDouble(tokeny[3]));
                        }
                    }   
                    in.close();
                    JOptionPane.showMessageDialog(null, "Średnia z przedmiotu "+przedmiotS+" wynosi: "+Double.toString(Ps.srednia()), "INFORMACJA", JOptionPane.PLAIN_MESSAGE); 
                }
                catch(FileNotFoundException er){
                    JOptionPane.showMessageDialog(null, "Brak podanego pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE); 
                }
                catch(IOException err){
                    JOptionPane.showMessageDialog(null, "Błąd podczas obsługi pliku!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
                }
                catch(ArrayIndexOutOfBoundsException ae){
                    JOptionPane.showMessageDialog(null, "Za dużo ocen w systemie!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }); 
        
        zamknij.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameS.dispose(); 
            } 
        }); 
    }
        });
    }
}
