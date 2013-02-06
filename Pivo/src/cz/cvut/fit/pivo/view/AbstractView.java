/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view;

import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.model.IModel;


/**
 *
 * @author honza
 */
public abstract class AbstractView extends javax.swing.JPanel implements IView {
    protected Controller controller;
    protected IModel model;


    public AbstractView(Controller controller, IModel model) {       
        this.controller = controller;
        this.model=model;       
    }

     
}
