/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import cz.fit.cvut.dpo.mvc.controller.FacadeController;
import cz.fit.cvut.dpo.mvc.model.IModel;
import cz.fit.cvut.dpo.mvc.model.Model;

import cz.fit.cvut.dpo.mvc.swing.ViewFacade;
import cz.fit.cvut.dpo.mvc.view.IView;


/**
 *
 * @author honza
 */
public class Main {
    
    public static void main(String[] args) {
        IModel model = new Model();        
        
        FacadeController facade = new FacadeController(model);  
        
        IView view= new ViewFacade(facade, model);
        
        facade.addView(view);
        
        view.show();
        
        
    }
    
}
