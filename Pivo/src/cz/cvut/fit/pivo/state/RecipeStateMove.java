/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IViewFacade;


public class RecipeStateMove extends RecipeState {
    double tempToMove;

    public RecipeStateMove(double tempToMove, IController controller, IViewFacade view, Kettle kettle) {
        super(controller, view, kettle);
        this.tempToMove = tempToMove;
    }  
    

    @Override
    public void handle(Recipe recipe, float temp) {
        if (temp>= (((float) tempToMove) - Constants.TOLERANCE)){
            //jsme v toleranci a meni se stav na hold  
            System.out.println("Menime stav na hold: "+tempToMove);
            kettle.setRecipeState(new RecipeStateHoldTemp(controller, view, kettle));
            view.drawNextPartOfRecipe();
                /*System.out.println("menime stav na odrmutovaci");
                controller.setRecipeState(new RecipeStateOdrmutovaci(controller, view));
                ((ViewFacadeFX) view).addOdrmutovaci();*/
            
        }
    }
    
    
}
