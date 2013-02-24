/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.controller;

import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.view.IView;

/**
 *
 * @author Adam
 */
public interface IController {
    void startCooking();
    void stopCooking();
    void resetCooking();
    void tick();
    void notifyView();
    IView getView();
    TempTime readTempTime();
    
}
