/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.controller;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.exceptions.ConnectionError;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.persistence.IPersistence;
import cz.cvut.fit.pivo.persistence.Persistence;
import cz.cvut.fit.pivo.state.RecipeState;
import cz.cvut.fit.pivo.state.RecipeStateVystrika;
import cz.cvut.fit.pivo.view.IView;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam
 */
public class Controller implements IController {
    IPersistence persistence;
    IModel model;
    IView view;
    MyTimer timer;
    RecipeState recipeState;

    public Controller(IModel model) {
        this.model = model;
        this.persistence = new Persistence();
        model.setRecipes(persistence.readRecipes());
    }

    public void setView(IView view) {
        this.view = view;        
        this.recipeState = new RecipeStateVystrika(this, view);
    }
    
    

    @Override
    public void startCooking() {
        model.start();
        view.start();
        this.timer = new MyTimer(1, this);
    }

    @Override
    public TempTime readTempTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stopCooking() {
        this.timer.cancel();
    }

    @Override
    public void resetCooking() {
        stopCooking();
        model.reset();
        model.start();
        view.reset();
    }

    @Override
    public void tick() {
        try {
            model.refresh();
            checkRecipe();
            view.notifyView();
            
        } catch (ConnectionError ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void notifyView() {
        view.notifyView();

    }
    
    @Override
    public IView getView(){
        return view;
    }
    
    
    
    void checkRecipe(){
        Recipe recipe = model.getCurrentRecipe();
        float temp = model.getCurrent().getTemp();
        if (!recipe.equals(new Recipe())) {
            recipeState.handle(recipe, temp);
        }
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        List<Recipe> recipes = model.getRecipes();
        recipes.add(recipe);
        persistence.saveRecipes(recipes);
    }
    
    public void saveGraph(BufferedImage image, String path) {
        persistence.saveGraph(image, path);
    }

    public RecipeState getRecipeState() {
        return recipeState;
    }

    public void setRecipeState(RecipeState recipeState) {
        this.recipeState = recipeState;
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        System.out.println("Mazu:"+ recipe);
        model.getRecipes().remove(recipe);
        model.setCurrentRecipe(null);
        persistence.saveRecipes(model.getRecipes());
        System.out.println("Recipe deleted");
        notifyView();
    }
    
    
}
