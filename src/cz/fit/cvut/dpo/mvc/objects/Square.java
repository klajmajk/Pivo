package cz.fit.cvut.dpo.mvc.objects;

import cz.fit.cvut.dpo.mvc.command.EnumShape;

public class Square extends AbstractShape {
	private int side; 
	
	public Square(Position position, int side) {
		super(position, EnumShape.SQUARE);
		this.side = side;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}	

}
