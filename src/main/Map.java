package main;

public class Map {
	private Layer bgLayer;
	private Layer objLayer;
	private int width;
	private int height;
	public static final int nullValue=-1;
	
	public Map(int width,int height) {
		this.width=width;
		this.height=height;
		bgLayer=new Layer(width,height);
		objLayer=new Layer(width,height,-1);
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
	public void set(int x,int y,int value,int index) {
		Layer select;
		if(index==0)
			select=bgLayer;
		else if(index==1)
			select=objLayer;
		else
			return;
		select.set(x, y, value);
	}
	
}
