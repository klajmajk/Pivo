/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view;

import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.model.IModel;
import javafx.application.Application;


/**
 *
 * @author honza
 */
public abstract class AbstractViewFX extends Application implements IView {
    protected Controller controller;
    protected IModel model;


    public AbstractViewFX(Controller controller, IModel model) {       
        this.controller = controller;
        this.model=model;       
    }

     
}
