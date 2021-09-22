/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazastudentow;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paulinka
 */
public class OknoBaza {

    public OknoBaza() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        final JTable table; final JButton zamykanieB = new JButton("ZAMKNIJ"), sNazw = new JButton("NAZWISKA"), sAlbum = new JButton("NUMERU ALBUMU");
        Pracownik Pr = new Pracownik();
        DefaultTableModel model = new DefaultTableModel(); 
        table = new JTable(model); 
        model.addColumn("Imię"); model.addColumn("Nazwisko"); model.addColumn("Numer albumu"); 
        model.addColumn("Pesel"); model.addColumn("Rok studiów"); 
        Pr.bazaS(model);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(5);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel zamykanie = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        zamykanie.add(zamykanieB);
        bottombtnPnl.add(new JLabel("SORTUJ WEDŁUG:"));
        bottombtnPnl.add(sNazw);
        bottombtnPnl.add(sAlbum);
        btnPnl.add(zamykanie, BorderLayout.NORTH);
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
        table.getTableHeader().setReorderingAllowed(false);
        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.add(table, BorderLayout.CENTER);
        frame.add(btnPnl, BorderLayout.SOUTH);

        frame.add(new JScrollPane(table));

        frame.setTitle("BAZA STUDENTÓW");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        
        zamykanieB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            } 
        }); 
        sNazw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                sNazw.setEnabled(false);
                sAlbum.setEnabled(true);
                Pr.listaS(model);
                Pr.bazaS(model);
            } 
        }); 
        sAlbum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                sAlbum.setEnabled(false);
                sNazw.setEnabled(true);
                Pr.listaP(model);
                Pr.bazaS(model);
            } 
        }); 
    }
    
    public OknoBaza(String imie, String nazwisko, String przedmiot) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        final JTable table; final JButton zamykanieB = new JButton("ZAMKNIJ"), sNazw = new JButton("NAZWISKA"), sKom = new JButton("KOMENTARZA");
        Przedmiot Pr = new Przedmiot(przedmiot);
        Osoba P = new Prowadzacy(Pr, imie, nazwisko);
        DefaultTableModel model = new DefaultTableModel(); 
        table = new JTable(model); 
        model.addColumn("Imię"); model.addColumn("Nazwisko"); model.addColumn("Przedmiot"); 
        model.addColumn("Ocena"); model.addColumn("Komentarz"); 
        P.bazaS(model);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(5);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel zamykanie = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        zamykanie.add(zamykanieB);
        bottombtnPnl.add(new JLabel("SORTUJ WEDŁUG:"));
        bottombtnPnl.add(sNazw);
        bottombtnPnl.add(sKom);
        btnPnl.add(zamykanie, BorderLayout.NORTH);
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
        table.getTableHeader().setReorderingAllowed(false);
        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.add(table, BorderLayout.CENTER);
        frame.add(btnPnl, BorderLayout.SOUTH);

        frame.add(new JScrollPane(table));

        frame.setTitle("BAZA STUDENTÓW");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        
        zamykanieB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            } 
        }); 
        sNazw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                sNazw.setEnabled(false);
                sKom.setEnabled(true);
                P.listaS(model);
                //P.bazaS(model);
            } 
        }); 
        sKom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                sNazw.setEnabled(true);
                sKom.setEnabled(false);
                P.listaP(model);
                //P.bazaS(model);
            } 
        }); 
    }
    
}
