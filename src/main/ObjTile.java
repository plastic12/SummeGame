package main;

public abstract class ObjTile implements Comparable<ObjTile>{
	protected int index;
	public abstract int getX();
	public abstract int getY();
	public ObjTile(int index) {
		this.index=index;
	}
	
	public int getIndex() {return index;}
	@Override
	public int compareTo(ObjTile o) {
		int x=o.getX();
		int y=o.getY();
		if(x!=getX())
			return (x>getX())? 1:-1;
		else if(y!=getY())
			return (y>getY())? 1:-1;
		else
			return 0;
	}
	
	
	
}
