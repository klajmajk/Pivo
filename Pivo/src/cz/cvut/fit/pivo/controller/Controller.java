/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.controller;

import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.exceptions.ConnectionError;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.IView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam
 */
public class Controller implements IController {

    IModel model;
    IView view;
    MyTimer timer;

    public Controller(IModel model) {
        this.model = model;
    }

    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void startCooking() {
        model.start();
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
        model.start();
    }

    @Override
    public void tick() {
        try {
            model.refresh();
            view.notifyView();
        } catch (ConnectionError ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void notifyView() {
        view.notifyView();

    }
}
