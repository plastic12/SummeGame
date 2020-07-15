package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Party extends ObjTile{
	private Map map;
	private IntegerProperty xPro;
	private IntegerProperty yPro;
	public Party(int index, int x, int y) {
		super(index);
		xPro=new SimpleIntegerProperty(x);
		yPro=new SimpleIntegerProperty(y);
	}
	public void setLayer(Map map) {
		this.map=map;
	}
	public Character[] characters=new Character[5];
	public Character[][] formation=new Character[3][3];
	
	public void move(int newX,int newY) {
		if(!map.canMove(newX, newY))
			return;
		else {
			map.move(this,getX(),getY(),newX,newY);
			xPro.set(newX);
			yPro.set(newY);

			//System.out.println((layer.wholemap[xPro.get()][yPro.get()]==null));
		}
	}
	public int getX() {return xPro.get();}
	public int getY() {return yPro.get();}
	public IntegerProperty xProperty() {return xPro;}
	public IntegerProperty yProperty() {return yPro;}
	public void moveUp() {
		move(getX(),getY()-1);
	}
	public void moveDown() {
		move(getX(),getY()+1);
	}
	public void moveRight() {
		move(getX()+1,getY());
	}
	public void moveLeft() {
		move(getX()-1,getY());
	}
	
	
	
	
	
	
	
	

}
