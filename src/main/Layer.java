package main;

public abstract class Layer {
	protected int width;
	protected int height;
	public static final int nullValue=-1;
	
	public Layer(int width,int height) {
		this.width=width;
		this.height=height;

	}
	//cameraX,cameraY is the position of top left hand corner
	public abstract int[][] display(int cameraX,int cameraY,int size);
	
}
