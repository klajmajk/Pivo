/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.dpo.mvc.command;

import cz.fit.cvut.dpo.mvc.model.IModel;
import cz.fit.cvut.dpo.mvc.objects.AbstractShape;
import cz.fit.cvut.dpo.mvc.objects.Circle;
import cz.fit.cvut.dpo.mvc.objects.Position;
import cz.fit.cvut.dpo.mvc.objects.Square;

/**
 *
 * @author honza
 */
public class CreateShapeCommand extends ICommand {

    private EnumShape enumShape;
    private Position position;
    
    public CreateShapeCommand(IModel model, EnumShape enumShape, Position position) {
        super(model);
        this.enumShape = enumShape;
        this.position = position;     
    }    
    
    @Override
    public void execute() {
        switch (enumShape) {
            case CIRCLE : model.addShape(new Circle(position, 30)); break;
            case SQUARE : model.addShape(new Square(position, 10)); break;                
        }
    }    
}
