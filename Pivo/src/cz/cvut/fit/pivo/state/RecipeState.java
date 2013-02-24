/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;

/**
 *
 * @author Adam
 */
public abstract class RecipeState {
    IView view;

    public RecipeState(IView view) {
        this.view = view;
    }
    
    /**
     *
     * @param recipe
     * @param temp
     */
    abstract public void handle(Recipe recipe, float temp);
}
