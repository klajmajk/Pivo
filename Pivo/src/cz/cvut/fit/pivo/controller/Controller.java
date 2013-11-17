/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.controller;

import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Settings;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.persistence.IPersistence;
import cz.cvut.fit.pivo.persistence.Persistence;
import cz.cvut.fit.pivo.state.RecipeStateHoldTemp;
import cz.cvut.fit.pivo.state.RecipeStateNull;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.IViewFacade;
import java.awt.image.BufferedImage;
import java.util.Set;

/**
 *
 * @author Adam
 */
public class Controller implements IController {
    IPersistence persistence;
    IModel model;
    IViewFacade view;
    MyTimer timer;

    public Controller(IModel model) {
        this.model = model;
        this.persistence = new Persistence();
        model.setRecipes((Set)persistence.readRecipes());
        model.setSettings(Settings.loadSettings());
    }

    public void setView(IView view) {
        this.view = (IViewFacade)view;       
    }
    
    private void resetKettles(){
        for (Kettle kettle : model.getKettles()) {            
            if (kettle.isInfusion()) kettle.setRecipeState(new RecipeStateHoldTemp(this, view, kettle)); 
            if (!kettle.isInfusion()) kettle.setRecipeState(new RecipeStateNull(this, view, kettle)); 
            
        }
    }

    @Override
    public void startCooking() {
        resetKettles();
        model.start();
        view.start();
        this.timer = new MyTimer(1, this);
    }

    

    @Override
    public void stopCooking() {
        if(timer!= null) this.timer.cancel();
        model.stop();
    }

    @Override
    public void resetCooking() {        
        resetKettles();
        stopCooking();
        model.reset();
        model.start();
        view.reset();
    }

    @Override
    public void tick() {
        model.refresh();
        checkRecipe();
        view.notifyView();
    }

    @Override
    public void notifyView() {
        view.notifyView();
    }
    
    @Override
    public IView getView(){
        return view;
    }
    
    
    /**
     * obstarává business logiku vareni pomoci recipe statu jednotlivých kettlů
     */
    void checkRecipe(){
        Recipe recipe = model.getCurrentRecipe();
        if (!recipe.equals(new Recipe())) {
            for (Kettle kettle : model.getKettles()) {
                kettle.recipeStateHandle(recipe);
            }
        }
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        Set<Recipe> recipes = model.getRecipes();
        model.addRecipe(recipe);
        persistence.saveRecipes(recipes);
    }
    
    @Override
    public void saveGraph(BufferedImage image) {
        persistence.saveGraph(image);
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
    
    @Override
    public void applicationExit(){
        this.stopCooking();
        System.out.println(model.getSettings());
        model.getSettings().saveSettings();
    }

    @Override
    public void setHeating(boolean heat, boolean infusion) {
        view.setHeatingInfusion(heat);
    }
    
    
}
