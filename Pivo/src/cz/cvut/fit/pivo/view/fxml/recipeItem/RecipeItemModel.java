/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml.recipeItem;

import cz.cvut.fit.pivo.entities.Recipe;

/**
 *
 * @author Adam
 */
public class RecipeItemModel {
    Recipe recipe;

    public RecipeItemModel(Recipe recipe) {
        this.recipe = recipe;
    }
    
    public Recipe getRecipe(){
        return recipe;
    }
    
    
}
