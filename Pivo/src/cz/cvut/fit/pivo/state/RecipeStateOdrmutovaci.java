/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;

public class RecipeStateOdrmutovaci extends RecipeStateHoldTemp {


    public RecipeStateOdrmutovaci() {
        super();
    }    
    
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        if (isTimeToChange(recipe.odrmutovaciTime)) {
            System.out.println("JE KONEC");
        }

    }
    
}
