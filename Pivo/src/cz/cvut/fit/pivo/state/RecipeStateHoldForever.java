/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IViewFacade;

/**
 *
 * @author Adam
 */
public class RecipeStateHoldForever extends RecipeStateHoldTemp{

    public RecipeStateHoldForever(IController controller, IViewFacade view, Kettle kettle) {
        super(controller, view, kettle);
    }

    @Override
    public void handle(Recipe recipe, float temp) {        
        heatTo(temp, recipe.getPrecidingNonDecoction(recipe.getActiveRest()).getTemp());
    }
    
    
}
