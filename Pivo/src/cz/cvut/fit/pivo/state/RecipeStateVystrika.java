/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;

public class RecipeStateVystrika extends RecipeStateHoldTemp {


    public RecipeStateVystrika() {
        super();
    }    
    
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        if (isTimeToChange(recipe.vystiraciTime)) {
            System.out.println("budeme zvysovat teplotu na peptonizacni");
            recipe.setRecipeState((RecipeState)new RecipeStateMove(recipe.peptonizacniTemp));
        }

    }
    
}
