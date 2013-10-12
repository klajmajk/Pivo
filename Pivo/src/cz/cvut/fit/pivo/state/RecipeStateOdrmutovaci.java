/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacadeFX;

public class RecipeStateOdrmutovaci extends RecipeStateHoldTemp {

    public RecipeStateOdrmutovaci(IController controller, IView view) {
        super(controller, view);
    }


    
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        if (isTimeToChange(recipe.odrmutovaciTime)) {
            ((ViewFacadeFX) view).textOutput("Je hotovo");
        }else{
            ((ViewFacadeFX) view).holdTemp(getName(), recipe.odrmutovaciTemp);
        }

    }

    @Override
    String getName() {
        return "odrmutovací";
    }

    @Override
    void setNewState(Recipe recipe) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
