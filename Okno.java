
package bazastudentow;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Okno extends JFrame{

    public Okno(){
        JFrame frame = new JFrame("OBSŁUGA UCZELNI");
        JTextField imie = new JTextField(), nazw = new JTextField();
        
        JButton student = new JButton("STUDENT"), prowadzacy = new JButton("PROWADZĄCY ZAJĘCIA"),
                pracownik = new JButton("PRACOWNIK DZIEKANATU"), zamknij = new JButton("ZAMKNIJ");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        panel.add(student); panel.add(prowadzacy); ; panel.add(pracownik);  panel.add(new JLabel());panel.add(zamknij);
        
        frame.setSize(300, 300);
        JPanel zawartosc = new JPanel(new GridBagLayout());
        frame.setContentPane(zawartosc);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLocationRelativeTo(null);
        
        student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prowadzacy.setEnabled(false);
                pracownik.setEnabled(false);
                try {
                    OknoS S = new OknoS();
                } catch (Wyjatek ex) {
                    Logger.getLogger(Okno.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        });
        
        prowadzacy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pracownik.setEnabled(false);
                student.setEnabled(false);
                OknoP P = new OknoP();
                }
        });
        
        pracownik.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                student.setEnabled(false);
                prowadzacy.setEnabled(false);
                try {
                    OknoPD Pd = new OknoPD();
                } catch (IOException ex) {
                    Logger.getLogger(Okno.class.getName()).log(Level.SEVERE, null, ex);
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

