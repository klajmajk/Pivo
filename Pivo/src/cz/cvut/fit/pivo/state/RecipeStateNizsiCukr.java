/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacade;

public class RecipeStateNizsiCukr extends RecipeStateHoldTemp {


    public RecipeStateNizsiCukr(IView view) {
        super(view);
    }        
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        if (isTimeToChange(recipe.nizsiCukrTime)){
            ((ViewFacade) view).increaseTemp(recipe.vyssiCukrTemp);
            setNewState(recipe);
        }else{
            ((ViewFacade) view).holdTemp(getName(),recipe.nizsiCukrTemp );        
        }

    }

    @Override
    String getName() {
        return "Nižší cukrotvorná";
    }

    @Override
    void setNewState(Recipe recipe) {
            recipe.setRecipeState((RecipeState)new RecipeStateMove(recipe.vyssiCukrTemp, view));
    }
     
    
    
}
