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
public interface PracownikU {
 
    public String[][] bazaS(DefaultTableModel model);
    public void listaS(DefaultTableModel model);
    public void listaP(DefaultTableModel model);
}
