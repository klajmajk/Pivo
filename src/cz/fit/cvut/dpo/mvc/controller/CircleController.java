/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.dpo.mvc.controller;

import cz.fit.cvut.dpo.mvc.command.EnumShape;
import cz.fit.cvut.dpo.mvc.model.IModel;
import cz.fit.cvut.dpo.mvc.objects.AbstractShape;
import cz.fit.cvut.dpo.mvc.objects.Circle;
import cz.fit.cvut.dpo.mvc.view.IView;
import java.util.List;

/**
 *
 * @author honza
 */
public class CircleController extends AbstractShapeController {

    public CircleController(IModel model) {
        super(model);
    }

    @Override
    public void changeSize(AbstractShape shape, int size) throws ClassCastException {
        Circle circle = (Circle) shape;
        circle.setRadius(size);
    }
    
}
