/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.dpo.mvc.controller;

import cz.fit.cvut.dpo.mvc.command.CreateShapeCommand;
import cz.fit.cvut.dpo.mvc.command.EnumShape;
import cz.fit.cvut.dpo.mvc.model.IModel;
import cz.fit.cvut.dpo.mvc.objects.AbstractShape;
import cz.fit.cvut.dpo.mvc.objects.Position;
import cz.fit.cvut.dpo.mvc.view.IView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author honza
 */
public class Controller {
    private List<IView> views=new ArrayList<>();
    private IModel model;
    
    public Controller( IModel model) {        
        this.model = model;
    }
    
    public void notifyAllViews() {
        for (IView view : views) {
            view.notifyView();
        }
    }
    
    public AbstractShape getAbstractShapeById(int id) {
      return model.getShapeById(id);
    }
    
    
    public void createShape(EnumShape enumShape, Position position) {
        System.out.println("controller create shape");
        model.executeCommand(new CreateShapeCommand(model, enumShape, position));
        notifyAllViews();
    } 
    
    public void addView(IView view){
        views.add(view);
    }
}
