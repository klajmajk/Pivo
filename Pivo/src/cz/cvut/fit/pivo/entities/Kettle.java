/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

import cz.cvut.fit.pivo.state.RecipeState;

/**
 * trida pro udrzovaní promennych spojenych s panvema
 * mozna se bude hodit ze se sem da ulozit vic veci.
 * @author Adam
 */
public class Kettle {
    boolean infusion;
    boolean heating;
    TempTime tempTime;    
    RecipeState recipeState;

    public Kettle(boolean infusion) {
        this.infusion = infusion;
        this.heating = false;
        
    }    

    public TempTime getTempTime() {
        return tempTime;
    }

    public void setTempTime(TempTime tempTime) {
        this.tempTime = tempTime;
    }

    public boolean isInfusion() {
        return infusion;
    }
    
    

    public boolean isHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public RecipeState getRecipeState() {
        return recipeState;
    }

    public void setRecipeState(RecipeState recipeState) {
        this.recipeState = recipeState;
    }
    
    public void recipeStateHandle(Recipe recipe){
        recipeState.handle(recipe, tempTime.getTemp());
    }
    
    
    
    
}
