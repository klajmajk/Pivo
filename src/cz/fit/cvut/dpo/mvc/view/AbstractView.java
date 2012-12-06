/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.dpo.mvc.view;

import cz.fit.cvut.dpo.mvc.controller.FacadeController;
import cz.fit.cvut.dpo.mvc.model.IModel;
import cz.fit.cvut.dpo.mvc.objects.AbstractShape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author honza
 */
public abstract class AbstractView extends javax.swing.JPanel implements IView {
    protected FacadeController controller;
    protected IModel model;
     protected List<AbstractShape> allShapes=new ArrayList<>();


    public AbstractView(FacadeController controller, IModel model) {       
        this.controller = controller;
        this.model=model;       
        allShapes = model.getAllShapes(); 
    }
     
}
