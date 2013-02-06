/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.main;

import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.model.Model;
import cz.cvut.fit.pivo.view.IView;

/**
 *
 * @author Adam
 */
public class Main {
    public static void main(String[] args) {
         IModel model = new Model();      
        Controller facade = new Controller(model); 
        IView view= new ViewFacade(facade, model);
        facade.addView(view);
        view.show();
    }
}
