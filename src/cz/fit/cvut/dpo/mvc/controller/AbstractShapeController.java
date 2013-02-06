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

/**
 *
 * @author honza
 */
public abstract class AbstractShapeController implements IShapeController {
    
    protected IModel model;

    public AbstractShapeController(IModel model) {
        this.model = model;
    }    

    @Override
    public void changePosition(AbstractShape shape, Position position) {
        shape.setPosition(position);
    }
    
    
   
    
}
