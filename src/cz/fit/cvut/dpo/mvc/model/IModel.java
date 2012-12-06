package cz.fit.cvut.dpo.mvc.model;

import cz.fit.cvut.dpo.mvc.command.ICommand;
import java.util.ArrayList;

import cz.fit.cvut.dpo.mvc.objects.AbstractShape;
import cz.fit.cvut.dpo.mvc.objects.Position;
import java.util.List;

public interface IModel {
    //get all elements

    public void addShape(AbstractShape shape);

    public void removeShape(AbstractShape shape);

    public List<AbstractShape> getAllShapes();

    public void clearAllShapes();

    public AbstractShape getShapeById(int id);
    
    public void executeCommand(ICommand com);
}
