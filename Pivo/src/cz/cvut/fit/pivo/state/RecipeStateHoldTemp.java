/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;

public abstract class RecipeStateHoldTemp extends RecipeState {

    long start;

    public RecipeStateHoldTemp() {
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
}
