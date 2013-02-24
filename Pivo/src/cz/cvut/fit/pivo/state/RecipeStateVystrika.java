/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacade;

public class RecipeStateVystrika extends RecipeStateHoldTemp {


    public RecipeStateVystrika(IView view) {
        super(view);
    }    
    
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {        
        if (isTimeToChange(recipe.vystiraciTime)) {            
            ((ViewFacade) view).increaseTemp(recipe.peptonizacniTemp);
            setNewState(recipe);
        }else{
            ((ViewFacade) view).holdTemp(getName(),recipe.vystiraciTemp );        
        }

    }

    @Override
    String getName() {
        return "vystírací";
    }

    @Override
    void setNewState(Recipe recipe) {
        recipe.setRecipeState((RecipeState)new RecipeStateMove(recipe.peptonizacniTemp, view));
    }
    
}
