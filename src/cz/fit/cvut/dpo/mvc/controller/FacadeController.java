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

/**
 *
 * @author honza
 */
public class FacadeController implements IShapeController {
    private CircleController circleCtrl;
    private SquareController squareCtrl;
    private Controller ctrl;

    public FacadeController(IModel model) {
        this.circleCtrl = new CircleController(model);
        this.squareCtrl = new SquareController(model);
        this.ctrl = new Controller(model);
    }       

    @Override
    public void changePosition(AbstractShape shape, Position position) {
        
    }

    @Override
    public void changeSize(AbstractShape shape, int size) throws ClassCastException {        
    }
    
     public void addView(IView view){
        ctrl.addView(view);
    }
    
    public void createShape(EnumShape enumShape, Position position) {
        ctrl.createShape(enumShape, position);
    } 
}
