/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.model;
import cz.cvut.fit.pivo.entities.TempTime;

/**
 *
 * @author Adam
 */
public interface IModel {
    public TempTime getCurrent();
    public void setCurrent(TempTime current);
    void addTempTimeReading();
    void loadProcedure();
}
