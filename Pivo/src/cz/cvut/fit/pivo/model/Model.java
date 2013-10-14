/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.model;

import cz.cvut.fit.pivo.arduino.Arduino;
import cz.cvut.fit.pivo.arduino.FakeArduino;
import cz.cvut.fit.pivo.arduino.IArduino;
import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Settings;
import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.exceptions.ConnectionError;
import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Adam
 */
public class Model implements IModel{
    TempTime current;
    TempTime current1;
    IArduino arduino;
    Time startTime;
    int badRequests;
    Recipe currentRecipe;
    Set<Recipe> recipes;
    boolean hasTwoSensors;
    boolean isRunning;
    Settings settings;

    public Model() {
        reset();
        settings = new Settings();
        //this.current = arduino.getTemp();
    }

    /**
     *
     * @return recipes
     */
    @Override
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    /**
     *
     * @param recipes
     */
    @Override
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public Settings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(Settings settings) {
        this.settings = settings;
        System.out.println(settings);
    }
    
    
    
    

    @Override
    public Time getStartTime() {
        return startTime;
    }

    @Override
    public Recipe getCurrentRecipe() {
        return currentRecipe;
    }

    @Override
    public void setCurrentRecipe(Recipe currentRecipe) {
        this.currentRecipe = currentRecipe;
        System.out.println(currentRecipe);
    }
    
    

    @Override
    public TempTime getCurrent() {
        return current;
    }

    
    @Override
    public void addTempTimeReading() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loadProcedure() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(){
        //try {
            List<TempTime> tempTimeList = arduino.getTemp();
            this.current = tempTimeList.get(0);
            this.current1 = tempTimeList.get(1);
            if(current1.getTemp()!=-1){
                hasTwoSensors = true;
                System.out.println("has two sensors");
            }else{
                hasTwoSensors = false;
            }
            badRequests = 0;
        /*} catch (IOException ex) {
            badRequests++;
            if(badRequests == Constants.MAX_BAD_REQUESTS) {
                throw new ConnectionError("Posledních "+ Constants.MAX_BAD_REQUESTS+" skončilo timeoutem");
            }
        }*/
    }

    @Override
    public void start() {
        startTime = new Time(System.currentTimeMillis());
        isRunning = true;
    }

    @Override
    public TempTime getCurrent1() {
        return current1;
    }

    @Override
    public boolean hasTwoSensors() {
        return hasTwoSensors;
    }

    @Override
    public final void reset() {
        //TODO musi se zmenit zpet
        arduino = new FakeArduino();
        badRequests = 0;
        this.current = new TempTime(0);
        this.startTime = new Time(0);
        currentRecipe = new Recipe();
        hasTwoSensors = false;
        isRunning = false;
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        Recipe toDelete= null;
        for (Recipe r : recipes) {
            if(r.name.equals(recipe.name)){ 
                toDelete = r;
            }
        }
        if(toDelete!=null){            
            recipes.remove(toDelete);         
        }
        recipes.add(recipe);
    }
    
    
}
