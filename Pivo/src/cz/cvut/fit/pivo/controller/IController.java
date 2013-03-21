/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.controller;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.state.RecipeState;
import cz.cvut.fit.pivo.view.IView;

/**
 *
 * @author Adam
 */
public interface IController {
    void startCooking();
    void stopCooking();
    void resetCooking();
    void tick();
    void notifyView();
    void saveRecipe(Recipe recipe);
    public RecipeState getRecipeState();
    public void setRecipeState(RecipeState recipeState);
    IView getView();
    TempTime readTempTime();
    
}
