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
    private Heater heater;
    TempTime tempTime;    
    RecipeState recipeState;

    public Kettle(boolean infusion) {
        this.heater = new Heater();
        this.infusion = infusion;
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
        return heater.isHeating();
    }

    public void setHeating(boolean heating) {
        heater.setHeating(heating);
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

    public Heater getHeater() {
        return heater;
    }
    
    

    @Override
    public String toString() {
        return "Kettle{" + "infusion=" + infusion + ", heater=" + heater + ", tempTime=" + tempTime + ", recipeState=" + recipeState + '}';
    }

    
    
    
    
    
    
    
}
