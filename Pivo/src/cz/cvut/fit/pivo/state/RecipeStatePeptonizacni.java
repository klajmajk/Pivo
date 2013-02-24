/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacade;

public class RecipeStatePeptonizacni extends RecipeStateHoldTemp {


    public RecipeStatePeptonizacni(IView view) {
        super(view);
    }    
    
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        if (isTimeToChange(recipe.peptonizacniTime)){
            ((ViewFacade) view).increaseTemp(recipe.nizsiCukrTemp);
            setNewState(recipe);
        }else{
            ((ViewFacade) view).holdTemp(getName(),recipe.peptonizacniTemp );        
        }

    }

    @Override
    String getName() {
        return "peptonizační";
    }

    @Override
    void setNewState(Recipe recipe) {
            recipe.setRecipeState((RecipeState)new RecipeStateMove(recipe.nizsiCukrTemp, view));
    }
    
}
