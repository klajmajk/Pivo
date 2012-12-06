package cz.fit.cvut.dpo.mvc.model;

import cz.fit.cvut.dpo.mvc.command.ICommand;
import cz.fit.cvut.dpo.mvc.objects.AbstractShape;
import cz.fit.cvut.dpo.mvc.objects.Circle;
import cz.fit.cvut.dpo.mvc.objects.Position;
import java.util.LinkedList;
import java.util.List;

public class Model implements IModel {
    
    private static int id = 0;
    private List<AbstractShape> shapeList;

    public Model() {
        shapeList = new LinkedList<AbstractShape>();
        addShape(new Circle(new Position(5, 10), 5));
    }
    
    @Override
    public void addShape(AbstractShape shape) {
        shape.setId(id);
        shapeList.add(id++, shape);
    }

    @Override
    public void removeShape(AbstractShape shape) {
        shapeList.remove(shape);
    }

    @Override
    public List<AbstractShape> getAllShapes() {
        return shapeList;
    }

    @Override
    public void clearAllShapes() {
        shapeList.clear();
    }

    @Override
    public AbstractShape getShapeById(int id) {
        return shapeList.get(id);
    }

    @Override
    public void executeCommand(ICommand com) {
        com.execute();
    }

    
}
