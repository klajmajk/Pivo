/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacadeFX;

public class RecipeStatePeptonizacni extends RecipeStateHoldTemp {

    public RecipeStatePeptonizacni(IController controller, IView view) {
        super(controller, view);
    } 
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        myTemp = recipe.peptonizacniTemp;
        super.handle(recipe, currentTemp);
        if (isTimeToChange(recipe.peptonizacniTime)){
            ((ViewFacadeFX) view).increaseTemp(recipe.nizsiCukrTemp);
            setNewState(recipe);
        }else{
            ((ViewFacadeFX) view).holdTemp(getName(),recipe.peptonizacniTemp );        
        }

    }

    @Override
    String getName() {
        return "peptonizační";
    }

    @Override
    void setNewState(Recipe recipe) {
            controller.setRecipeState((RecipeState)new RecipeStateMove(recipe.nizsiCukrTemp,controller,  view));
    }
    
}
