package main;

public interface ObjTile {
	public int getX();
	public int getY();
	
	public int getIndex();
	public default Position getPosition() {
		return new Position(getX(),getY());
	}
	
	
	
}
