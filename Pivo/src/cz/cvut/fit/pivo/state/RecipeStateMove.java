/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.state;

import cz.cvut.fit.pivo.entities.Recipe;
import sun.jdbc.odbc.OdbcDef;


public class RecipeStateMove extends RecipeState {
    int tempToMove;

    public RecipeStateMove(int tempToMove) {
        this.tempToMove = tempToMove;
    }
    
    

    @Override
    public void handle(Recipe recipe, float temp) {
        if (temp>= (((float) tempToMove) - recipe.tolerance)){
            if(recipe.peptonizacniTemp == tempToMove){
                System.out.println("Menime stav na peptonizacni");
                recipe.setRecipeState(new RecipeStatePeptonizacni());
            }else if(recipe.nizsiCukrTemp == tempToMove){     
                System.out.println("menime stav na nizsi cukr");
                recipe.setRecipeState(new RecipeStateNizsiCukr());
            }else if(recipe.vyssiCukrTemp == tempToMove){     
                System.out.println("menime stav na vyssi cukr");
                recipe.setRecipeState(new RecipeStateVyssiCukr());
            }else if(recipe.odrmutovaciTemp == tempToMove){   
                System.out.println("menime stav na odrmutovaci");
                recipe.setRecipeState(new RecipeStateOdrmutovaci());
            }
        }
    }
    
    
}
