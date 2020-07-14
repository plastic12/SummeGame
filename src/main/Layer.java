package main;

public class Layer {
	private int[][] wholemap;
	private int width;
	private int height;
	public static final int nullValue=-1;
	
	public Layer(int width,int height) {
		wholemap=new int[width][height];
		this.width=width;
		this.height=height;
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				wholemap[i][j]=0;
			}
		}
	}
	public Layer(int width,int height,int defaultValue) {
		wholemap=new int[width][height];
		this.width=width;
		this.height=height;
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				wholemap[i][j]=defaultValue;
			}
		}
	}
	//cameraX,cameraY is the position of top left hand corner
	public int[][] display(int cameraX,int cameraY,int size){
		int[][] output=new int[size][size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				int newX=cameraX+i;
				int newY=cameraY+j;
				if(newX<0||newY<0||newX>=width||newY>=height) {
					output[i][j]=nullValue;
				}
				else
					output[i][j]=wholemap[newX][newY];
			}
		}
		return output;
	}
	public void set(int x,int y,int value) {
		wholemap[x][y]=value;
	}
	
}
