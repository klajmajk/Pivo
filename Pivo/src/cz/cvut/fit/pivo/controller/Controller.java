/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.controller;

import cz.cvut.fit.pivo.arduino.FakeArduino;
import cz.cvut.fit.pivo.arduino.IArduino;
import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Settings;
import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.persistence.IPersistence;
import cz.cvut.fit.pivo.persistence.Persistence;
import cz.cvut.fit.pivo.state.RecipeStateHoldTemp;
import cz.cvut.fit.pivo.state.RecipeStateNull;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.IViewFacade;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
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
    private IArduino arduino;
    private int badRequests;

    public Controller(IModel model) {
        this.model = model;
        this.persistence = new Persistence();        
        this.arduino = new FakeArduino();
        model.setRecipes((Set) persistence.readRecipes());
        model.setSettings(Settings.loadSettings());
    }
    
    public void refresh(){
         try {
            List<TempTime> tempTimeList = arduino.getTemp();
            for (Kettle kettle : model.getKettles()) {
                if (kettle.isInfusion()) {
                    kettle.setTempTime(tempTimeList.get(0));
                } else {
                    kettle.setTempTime(tempTimeList.get(1));
                }
            }
            if (tempTimeList.get(1).getTemp() != -1) {
                model.setHasTwoSensors(true);
            } else {
                model.setHasTwoSensors(false);
            }
            badRequests = 0;
        } catch (IOException ex) {
            badRequests++;
            if (badRequests == Constants.MAX_BAD_REQUESTS) {
                System.out.println("Posledních " + Constants.MAX_BAD_REQUESTS + " skončilo timeoutem");
            }
        }
    }

    public void setView(IView view) {
        this.view = (IViewFacade) view;
        this.timer = new MyTimer(1, this);
    }

    private void resetKettles() {
        model.getKettle(true).setRecipeState(new RecipeStateHoldTemp(this, view, model.getKettle(true)));
        model.getKettle(false).setRecipeState(new RecipeStateNull(this, view, model.getKettle(false)));
    }

    @Override
    public Kettle getKettle(boolean infusion) {
        return model.getKettle(infusion);
    }

    @Override
    public void startCooking() {
        resetKettles();
        model.start();
        view.start();
    }

    @Override
    public void stopCooking() {
        model.stop();
    }
    
    

    @Override
    public void resetCooking() {
        
        stopCooking();
        resetKettles();
        model.reset();
        view.reset();
        System.out.println("Resetovano");
    }

    @Override
    public void tick() {
        refresh();
        if (model.isRunning()) {
            checkRecipe();
        }
        view.notifyView();
    }

    @Override
    public void notifyView() {
        view.notifyView();
    }

    @Override
    public IView getView() {
        return view;
    }

    /**
     * obstarává business logiku vareni pomoci recipe statu jednotlivých kettlů
     */
    void checkRecipe() {
        Recipe recipe = model.getCurrentRecipe();
        if (!recipe.equals(new Recipe())) {
            model.getKettle(true).recipeStateHandle(recipe);
            model.getKettle(false).recipeStateHandle(recipe);
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
        persistence.saveGraphWithDialog(image);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        System.out.println("Mazu:" + recipe);
        model.getRecipes().remove(recipe);
        model.setCurrentRecipe(null);
        persistence.saveRecipes(model.getRecipes());
        System.out.println("Recipe deleted");
        notifyView();
    }

    @Override
    public void applicationExit() {
        this.stopCooking();
        System.out.println(model.getSettings());
        model.getSettings().saveSettings();
        timer.cancel();
    }

    @Override
    public void setHeating(boolean heat, boolean infusion) {
        model.getKettle(infusion).setHeating(heat);
        view.setHeating(heat, infusion);
    }

    @Override
    public void setRunningDecoction(boolean b) {
        model.setRunningDecoction(b);
    }

    @Override
    public void brewingFinished() {
        persistence.saveGraphWithoutDialog(((ViewFacadeFX) view).getChartImage(),
                model.getSettings().getGraphDefaultSavePath(), model.getCurrentRecipe().getName());
        ((ViewFacadeFX) view).brewingEnd();
    }

    @Override
    public void recipeSelected(Recipe recipe) {
        model.setCurrentRecipe(recipe);
        view.notifyRecipeChanged();
        view.notifyView();
    }

    @Override
    public void setHeatingOutput(int heating, boolean infusion) {
        arduino.setHeatingOutput(heating, infusion);
    }
}
