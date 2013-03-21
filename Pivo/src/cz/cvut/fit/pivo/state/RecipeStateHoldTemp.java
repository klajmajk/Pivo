/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;

public abstract class RecipeStateHoldTemp extends RecipeState {

    long start;

    public RecipeStateHoldTemp(IController controller, IView view) {
        super(controller, view);
        start = System.currentTimeMillis();
    }

   boolean isTimeToChange(int length) {
       System.out.println("Cas:"+(int) ((System.currentTimeMillis() - start) / 60000));
        if ((int) ((System.currentTimeMillis() - start) / 60000) >= length) {
            return true;
        } else {
            return false;
        }
    }
   
   abstract String getName();
   
   abstract void setNewState(Recipe recipe);
}
