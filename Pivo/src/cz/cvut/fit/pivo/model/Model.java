/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.model;

import cz.cvut.fit.pivo.arduino.Arduino;
import cz.cvut.fit.pivo.entities.TempTime;

/**
 *
 * @author Adam
 */
public class Model implements IModel{
    TempTime current;
    Arduino arduino;

    public Model() {
        arduino = new Arduino();
        this.current = arduino.getTemp();
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
    
}
