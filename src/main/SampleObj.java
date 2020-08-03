package main;

public class SampleObj implements ObjTile{
	public int x;
	public int y;
	public SampleObj(int x,int y) {
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

	@Override
	public int getIndex() {
		return -1;
	}

}
