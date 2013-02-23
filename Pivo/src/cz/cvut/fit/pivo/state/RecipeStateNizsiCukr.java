/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;

public class RecipeStateNizsiCukr extends RecipeStateHoldTemp {


    public RecipeStateNizsiCukr() {
        super();
    }        
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        if (isTimeToChange(recipe.nizsiCukrTime)) {
            recipe.setRecipeState((RecipeState)new RecipeStateMove(recipe.vyssiCukrTemp));
        }

    }
     
    
    
}
