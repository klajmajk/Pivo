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
import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.exceptions.ConnectionError;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

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
    List<Recipe> recipes;
    boolean hasTwoSensors;
    boolean isRunning;

    public Model() {
        reset();
        //this.current = arduino.getTemp();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     *
     * @param recipes
     */
    @Override
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
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
    public void refresh() throws ConnectionError{
        try {
            this.current = arduino.getTemp().get(0);
            this.current1 = arduino.getTemp().get(1);
            if(current1.getTemp()!=-1){
                hasTwoSensors = true;
                System.out.println("has two sensors");
            }else{
                hasTwoSensors = false;
            }
            badRequests = 0;
        } catch (IOException ex) {
            badRequests++;
            if(badRequests == Constants.MAX_BAD_REQUESTS) {
                throw new ConnectionError("Posledních "+ Constants.MAX_BAD_REQUESTS+" skončilo timeoutem");
            }
        }
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
    
    
}
