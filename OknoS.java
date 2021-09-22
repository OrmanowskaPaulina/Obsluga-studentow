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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class OknoS extends JFrame{

    public OknoS() throws Wyjatek{
        JFrame frameS = new JFrame("STUDENT");
        JTextField imie = new JTextField(), nazw = new JTextField();
        JButton zatwierdz = new JButton("ZATWIERDŹ DANE"), zamknij = new JButton("ZAMKNIJ");

        JPanel panel = new JPanel(); JPanel panel2 = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        panel.add( new JLabel("Imię: ") ); 
        panel.add(imie); 

        panel.add( new JLabel("Nazwisko: ") ); 
        panel.add(nazw); 
        panel.add(new JLabel()); panel.add(zatwierdz); panel.add(new JLabel()); panel.add(zamknij); 
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel2.add(scrollPane);
        
        frameS.setSize(600, 400);
        JPanel zawartosc = new JPanel(new GridBagLayout());
        frameS.setContentPane(zawartosc);
        frameS.add(panel);
        frameS.add(panel2);
        frameS.setVisible(true);
        frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frameS.setLocationRelativeTo(null);
        
        zatwierdz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zatwierdz.setEnabled(false);
                try{
                    String im = imie.getText(), nz = nazw.getText(), text = "Twoje oceny: \n";
                    String line; String []tokeny = new String[5];
                    BufferedReader in = new BufferedReader(new FileReader("oceny.txt"));
                    textArea.append(text);
                    panel.revalidate();
                    while ((line = in.readLine()) != null) {
                        tokeny = line.split(" ");
                        if (im.equalsIgnoreCase(tokeny[0]) && nz.equalsIgnoreCase(tokeny[1])){
                            String appendText = "Przedmiot: " + tokeny[2] + " Ocena: " + tokeny[3]+" Opis: "+tokeny[4]+"\n";
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
                System.exit(0); 
            } 
        }); 
    }
    
    
}
