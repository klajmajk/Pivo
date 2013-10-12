/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacadeFX;

public class RecipeStateVystrika extends RecipeStateHoldTemp {

    public RecipeStateVystrika(IController controller, IView view) {
        super(controller, view);
    }     
    
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {        
        System.out.println("REcipe cooking: " + recipe);
        if (isTimeToChange(recipe.vystiraciTime)) {            
            ((ViewFacadeFX) view).increaseTemp(recipe.peptonizacniTemp);
            setNewState(recipe);
        }else{
            ((ViewFacadeFX) view).holdTemp(getName(),recipe.vystiraciTemp );        
        }

    }

    @Override
    String getName() {
        return "vystírací";
    }

    @Override
    void setNewState(Recipe recipe) {
        controller.setRecipeState((RecipeState)new RecipeStateMove(recipe.peptonizacniTemp, controller,  view));
    }
    
}
