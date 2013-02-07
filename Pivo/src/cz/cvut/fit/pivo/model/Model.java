/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.model;

import cz.cvut.fit.pivo.arduino.Arduino;
import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.exceptions.ConnectionError;
import java.io.IOException;
import java.sql.Time;

/**
 *
 * @author Adam
 */
public class Model implements IModel{
    TempTime current;
    Arduino arduino;
    Time startTime;
    int badRequests;
    Recipe currentRecipe;

    public Model() {
        arduino = new Arduino();
        badRequests = 0;
        this.current = new TempTime(0);
        this.startTime = new Time(0);
        currentRecipe = new Recipe();
        //this.current = arduino.getTemp();
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
    public void setCurrent(TempTime current) {
        this.current = current;
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
            this.current = arduino.getTemp();
            badRequests = 0;
        } catch (IOException ex) {
            badRequests++;
            if(badRequests == Constants.MAX_BAD_REQUESTS) throw new ConnectionError("Posledních "+ Constants.MAX_BAD_REQUESTS+" skončilo timeoutem");
        }
    }

    @Override
    public void start() {
        startTime = new Time(System.currentTimeMillis());
    }
    
    
}
