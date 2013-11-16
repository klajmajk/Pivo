/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IViewFacade;

/**
 *
 * @author Adam
 */
public abstract class RecipeState{
    IViewFacade view;
    IController controller;

    public RecipeState(IController controller, IViewFacade view) {
        this.view = view;
        this.controller = controller;
    }
    
    /**
     * Tahle metoda obstarává funkci vzoru state
     * 
     * @param recipe toto je recept ktery se vaří
     * @param temp toto je současná teplota
     */
    abstract public void handle(Recipe recipe, float temp);
}
