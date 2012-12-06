package cz.fit.cvut.dpo.mvc.objects;

import cz.fit.cvut.dpo.mvc.command.EnumShape;

public class Circle extends AbstractShape {
	private int radius;

	
	public Circle(Position position, int radius) {
		super(position, EnumShape.CIRCLE);
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
}
