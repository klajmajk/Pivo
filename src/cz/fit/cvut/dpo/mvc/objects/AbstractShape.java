package cz.fit.cvut.dpo.mvc.objects;

import cz.fit.cvut.dpo.mvc.command.EnumShape;

public abstract class AbstractShape {

    private int id;
    private Position position;
    private EnumShape type;

    public EnumShape getType() {
        return type;
    }

    public AbstractShape(Position position,EnumShape type) {
        this.position = position;
        this.type=type;
    }

    public Position getPosition() {
        return position;
    }

    //change position
    public void setPosition(Position position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
