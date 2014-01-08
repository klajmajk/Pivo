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
        //System.out.println("Cas:" + (int) ((System.currentTimeMillis() - start) / 60000));
        if ((int) ((System.currentTimeMillis() - start) / 60000) >= length) {
            return true;
        } else {
            return false;
        }
    }

    

    @Override
    public void handle(Recipe recipe, float temp) {
        heatTo(temp, recipe.getActiveRest().getTemp());
        if (isTimeToChange(recipe.getActiveRest().getLength())) {
            if (recipe.hasNextRest()) {
                setNewState(recipe);
            } else {
                //je konec receptu
                controller.stopCooking();
                controller.brewingFinished();

            }

        } else {
            ((ViewFacadeFX) view).holdTemp(recipe.getActiveRest().getRestsType().toString(), recipe.getActiveRest().getTemp());
        }
    }

    public void setNewState(Recipe recipe) {
        Rest prev = recipe.getActiveRest();
        recipe.moveToNextRest();
        //přechod z infuze do dekokce
        if ((!prev.isDecoction()) && (recipe.getActiveRest().isDecoction())) {
            //pro jistotu vypni topeni 
            turnOffKettlesHeating();
            view.showInformationDialog("Potvrď, že proběhlo přečerpání.");
            kettle.setRecipeState(new RecipeStateHoldForever(controller, view, kettle));
            controller.getKettle(false).setRecipeState(new RecipeStateMove(
                    recipe.getActiveRest().getTemp(), controller, view, controller.getKettle(false)));
            controller.setRunningDecoction(true);
            //obraceny prechod
        } else if ((prev.isDecoction()) && (!recipe.getActiveRest().isDecoction())) {
            //pro jistotu vypni topeni 
            turnOffKettlesHeating();
            view.showInformationDialog("Potvrď, že proběhlo přečerpání.");
            kettle.setRecipeState(new RecipeStateNull(controller, view, kettle));
            controller.getKettle(true).setRecipeState(new RecipeStateMove(
                    recipe.getActiveRest().getTemp(), controller, view, controller.getKettle(true)));
            controller.setRunningDecoction(false);
            //zbytek
        } else {
            kettle.setRecipeState((RecipeState) new RecipeStateMove(recipe.getActiveRest().getTemp(), controller, view, kettle));
        }
        ((ViewFacadeFX) view).increaseTemp(recipe.getActiveRest().getTemp(), kettle.isInfusion());
    }

    private void turnOffKettlesHeating() {
        controller.getKettle(true).setHeating(false);
        controller.getKettle(false).setHeating(false);
    }
}
