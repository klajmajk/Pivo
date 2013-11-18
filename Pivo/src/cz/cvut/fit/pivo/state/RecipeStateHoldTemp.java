/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Rest;
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
        System.out.println("Cas:" + (int) ((System.currentTimeMillis() - start) / 60000));
        if ((int) ((System.currentTimeMillis() - start) / 60000) >= length) {
            return true;
        } else {
            return false;
        }
    }

    public void handleTemp(float tempToHold, float tempCurrent) {
        //System.out.println("heating check to hold:"+ tempToHold+ " temp current: "+tempCurrent+" is infusion"+ kettle.isInfusion());
        if (tempCurrent > Settings.getTempTolerance() + tempToHold) {
            //System.out.println("heating OFF to hold:"+ tempToHold+ " temp current: "+tempCurrent+" is infusion"+ kettle.isInfusion());
            controller.setHeating(false, kettle.isInfusion());
        }
        if (tempCurrent < tempToHold - Settings.getTempTolerance()) {      
            
        //System.out.println("heating ON to hold:"+ tempToHold+ " temp current: "+tempCurrent+" is infusion"+ kettle.isInfusion());
            controller.setHeating(true, kettle.isInfusion());
        }
    }

    @Override
    public void handle(Recipe recipe, float temp) {
        handleTemp(recipe.getActiveRest().getTemp(), temp);
        if (isTimeToChange(recipe.getActiveRest().getLength())) {
            if (recipe.hasNextRest()) {
                setNewState(recipe);
            } else {
                //je konec receptu
                controller.stopCooking();
                ((ViewFacadeFX) view).brewingEnd();
            }

        } else {
            ((ViewFacadeFX) view).holdTemp(recipe.getActiveRest().getRestsType().toString(), recipe.getActiveRest().getTemp());
        }
    }

    public void setNewState(Recipe recipe) {
        Rest prev = recipe.getActiveRest();        
        recipe.moveToNextRest();
        //přechod z infuze do dekokce
        if((!prev.isDecoction())&&(recipe.getActiveRest().isDecoction())){
            //TODO tady musi byt blokujici dialog potvrzujici precerpani
            view.showInformationDialog("tady musi byt blokujici dialog potvrzujici precerpani");
            kettle.setRecipeState(new RecipeStateHoldForever(controller, view, kettle));
            controller.getKettle(false).setRecipeState(new RecipeStateMove(
                    recipe.getActiveRest().getTemp(), controller, view, controller.getKettle(false)));
            ((ViewFacadeFX) view).increaseTemp(recipe.getActiveRest().getTemp(),false);
        //obraceny prechod
        }else if ((prev.isDecoction())&&(!recipe.getActiveRest().isDecoction())){
            //TODO tady musi byt blokujici dialog potvrzujici precerpani
            view.showInformationDialog("tady musi byt blokujici dialog potvrzujici precerpani");
            kettle.setRecipeState(new RecipeStateNull(controller, view, kettle));            
            controller.getKettle(true).setRecipeState(new RecipeStateMove(
                    recipe.getActiveRest().getTemp(), controller, view, controller.getKettle(true)));
            
            ((ViewFacadeFX) view).increaseTemp(recipe.getActiveRest().getTemp(),true);
        //zbytek
        }else{
            kettle.setRecipeState((RecipeState) new RecipeStateMove(recipe.getActiveRest().getTemp(), controller, view, kettle));
            ((ViewFacadeFX) view).increaseTemp(recipe.getActiveRest().getTemp(),kettle.isInfusion());
        }
    }
}
