package main;

public class Map {
	private BGLayer bgLayer;
	private ObjLayer objLayer;
	private int width;
	private int height;
	public static final int nullValue=-1;
	
	public Map(int width,int height) {
		this.width=width;
		this.height=height;
		bgLayer=new BGLayer(width,height,60);
		objLayer=new ObjLayer(width,height);
	}
	//cameraX,cameraY is the position of top left hand corner
	public int[][] display(int cameraX,int cameraY,int size,int index){
		switch(index) {
		case 0:
			return bgLayer.display(cameraX, cameraY, size);
		case 1:
			return objLayer.display(cameraX, cameraY, size);
		default:
			return null;
		}
	}
	public void setBG(int x,int y,int value) {
		bgLayer.set(x,y,value);
	}
	public void setObj(ObjTile t) {
		objLayer.set(t);
	}
	public void move(int x,int y,int newX,int newY) {
		objLayer.move(x, y, newX, newY);
	}
	public ObjLayer getObjectLayer() {return objLayer;}
	public boolean canMove(int x,int y) {
		if(objLayer.outOfBound(x, y))
			return false;
		else
			return bgLayer.canPass(x, y)&&objLayer.canPass(x, y);
	}
}
