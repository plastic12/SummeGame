package main;

public class SimpleObj extends ObjTile{

	private int x;
	private int y;
	
	public SimpleObj(int index,int x,int y) {
		super(index);
		this.x=x;
		this.y=y;
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	

}
