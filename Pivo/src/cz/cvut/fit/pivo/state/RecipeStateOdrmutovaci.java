/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.swing.CurrentView;
import cz.cvut.fit.pivo.view.AbstractView;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacade;

public class RecipeStateOdrmutovaci extends RecipeStateHoldTemp {


    public RecipeStateOdrmutovaci(IView view) {
        super(view);
    }    
    
   

    @Override
    public void handle(Recipe recipe, float currentTemp) {
        if (isTimeToChange(recipe.odrmutovaciTime)) {
            ((ViewFacade) view).textOutput("Je hotovo");
        }else{
            ((ViewFacade) view).holdTemp(getName(), recipe.odrmutovaciTemp);
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
