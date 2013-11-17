/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Settings;
import cz.cvut.fit.pivo.view.IViewFacade;
import cz.cvut.fit.pivo.view.ViewFacadeFX;

public class RecipeStateHoldTemp extends RecipeState {

    long start;
    float myTemp;

    public RecipeStateHoldTemp(IController controller, IViewFacade view, Kettle kettle) {
        super(controller, view, kettle);
        start = System.currentTimeMillis();
    }

   boolean isTimeToChange(int length) {
       System.out.println("Cas:"+(int) ((System.currentTimeMillis() - start) / 60000));
        if ((int) ((System.currentTimeMillis() - start) / 60000) >= length) {
            controller.setHeating(true, kettle.isHeating());
            return true;
        } else {
            return false;
        }
    }
   public void handleTemp(float tempToHold, float tempCurrent){
       System.out.println(tempCurrent+"- cur, "+tempToHold);
       if(tempCurrent> Settings.getTempTolerance()+tempToHold) controller.setHeating(false, kettle.isHeating());
       if(tempCurrent< tempToHold-Settings.getTempTolerance()) controller.setHeating(true, kettle.isHeating());
   }

    @Override
    public void handle(Recipe recipe, float temp) {
        handleTemp(recipe.getActiveRest().getTemp(), temp);
         if (isTimeToChange(recipe.getActiveRest().getLength())) {      
             if(recipe.hasNextRest()){
                recipe.moveToNextRest();
                ((ViewFacadeFX) view).increaseTemp(recipe.getActiveRest().getTemp());
                setNewState(recipe);
             }else{
                 //je konec receptu
                 controller.stopCooking();
                 ((ViewFacadeFX) view).brewingEnd();
             }
            
        }else{
            ((ViewFacadeFX) view).holdTemp(recipe.getActiveRest().getRestsType().toString(),recipe.getActiveRest().getTemp());        
        }         
    }
   
   
   
   
   
   public void setNewState(Recipe recipe){
       kettle.setRecipeState((RecipeState)new RecipeStateMove(recipe.getActiveRest().getTemp(), controller,  view, kettle));
   }
}
