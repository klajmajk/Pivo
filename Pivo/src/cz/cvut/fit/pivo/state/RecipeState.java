/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;

/**
 *
 * @author Adam
 */
public abstract class RecipeState{
    IView view;
    IController controller;

    public RecipeState(IController controller, IView view) {
        this.view = view;
        this.controller = controller;
    }
    
    /**
     *
     * @param recipe
     * @param temp
     */
    abstract public void handle(Recipe recipe, float temp);
}
