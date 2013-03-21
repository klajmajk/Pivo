/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacade;

public class RecipeStateVyssiCukr extends RecipeStateHoldTemp {

    public RecipeStateVyssiCukr(IController controller, IView view) {
        super(controller, view);
    }


      
    
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        if (isTimeToChange(recipe.vyssiCukrTime))  {
            ((ViewFacade) view).increaseTemp(recipe.odrmutovaciTemp);
            setNewState(recipe);
        }else{
            ((ViewFacade) view).holdTemp(getName(),recipe.vyssiCukrTemp );        
        }

    }

    @Override
    String getName() {
        return "vyšší cukrotvorná";
    }

    @Override
    void setNewState(Recipe recipe) {
        controller.setRecipeState((RecipeState)new RecipeStateMove(recipe.odrmutovaciTemp,controller, view));
    }
    
}
