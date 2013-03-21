/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.swing;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Adam
 */
class DeletePopUp extends JPopupMenu {
    JMenuItem delete;
    IController controller;
    Recipe recipe;
    public DeletePopUp(IController controller, Recipe recipe){
        delete = new JMenuItem("Smazat");
        this.controller = controller;
        this.recipe = recipe;
        add(delete);
        delete.addMouseListener(new DeleteMouseAdapter(controller, recipe));
    }
}