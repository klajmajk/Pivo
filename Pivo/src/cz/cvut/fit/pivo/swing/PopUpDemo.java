/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.swing;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Adam
 */
class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo(){
        anItem = new JMenuItem("Smazat");
        add(anItem);
    }
}