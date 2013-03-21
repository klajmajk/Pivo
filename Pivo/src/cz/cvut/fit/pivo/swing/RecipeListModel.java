/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.swing;

import cz.cvut.fit.pivo.entities.Recipe;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Adam
 */
public class RecipeListModel extends DefaultListModel {
    List<Recipe> recipes;

    public RecipeListModel(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    
    

    @Override
    public int getSize() {
        return recipes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return recipes.get(index);
    }


    
    
}
