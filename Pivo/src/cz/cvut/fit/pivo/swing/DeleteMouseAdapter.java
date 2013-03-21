/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.swing;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Adam
 */
public class DeleteMouseAdapter extends MouseAdapter {

    IController controller;
    Recipe recipe;

    public DeleteMouseAdapter(IController controller, Recipe recipe) {
        this.controller = controller;
        this.recipe = recipe;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        controller.deleteRecipe(recipe);
    }
}
