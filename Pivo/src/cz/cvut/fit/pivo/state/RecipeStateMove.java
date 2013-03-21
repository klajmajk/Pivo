/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacade;
import sun.jdbc.odbc.OdbcDef;


public class RecipeStateMove extends RecipeState {
    int tempToMove;

    public RecipeStateMove(int tempToMove, IController controller, IView view) {
        super(controller, view);
        this.tempToMove = tempToMove;
    }
    
    

    @Override
    public void handle(Recipe recipe, float temp) {
        if (temp>= (((float) tempToMove) - recipe.tolerance)){
            if(recipe.peptonizacniTemp == tempToMove){
                System.out.println("Menime stav na peptonizacni");
                controller.setRecipeState(new RecipeStatePeptonizacni(controller, view));
                ((ViewFacade) view).addPeptonizacni();
            }else if(recipe.nizsiCukrTemp == tempToMove){     
                System.out.println("menime stav na nizsi cukr");
                controller.setRecipeState(new RecipeStateNizsiCukr(controller, view));
                ((ViewFacade) view).addNizsiCukrotvorna();
            }else if(recipe.vyssiCukrTemp == tempToMove){     
                System.out.println("menime stav na vyssi cukr");
                controller.setRecipeState(new RecipeStateVyssiCukr(controller, view));
                ((ViewFacade) view).addVyssiCukrotvorna();
            }else if(recipe.odrmutovaciTemp == tempToMove){   
                System.out.println("menime stav na odrmutovaci");
                controller.setRecipeState(new RecipeStateOdrmutovaci(controller, view));
                ((ViewFacade) view).addOdrmutovaci();
            }
        }
    }
    
    
}
