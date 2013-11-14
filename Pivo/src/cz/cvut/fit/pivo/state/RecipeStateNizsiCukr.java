/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacadeFX;

public class RecipeStateNizsiCukr extends RecipeStateHoldTemp {


    public RecipeStateNizsiCukr(IController controller, IView view) {
        super(controller, view);
    }        
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        myTemp = recipe.nizsiCukrTemp;
        super.handle(recipe, currentTemp);
        if (isTimeToChange(recipe.nizsiCukrTime)){
            ((ViewFacadeFX) view).increaseTemp(recipe.vyssiCukrTemp);
            setNewState(recipe);
        }else{
            ((ViewFacadeFX) view).holdTemp(getName(),recipe.nizsiCukrTemp );        
        }

    }

    @Override
    String getName() {
        return "Nižší cukrotvorná";
    }

    @Override
    void setNewState(Recipe recipe) {
            controller.setRecipeState((RecipeState)new RecipeStateMove(recipe.vyssiCukrTemp,controller, view));
    }
     
    
    
}
