package main;

public interface Movable extends ObjTile{
	public void move(int newX,int newY);
	public void setX(int x);
	public void setY(int y);

}
