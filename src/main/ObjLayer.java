package main;

public class ObjLayer extends Layer{
	public ObjTile[][] wholemap;
	
	public ObjLayer(int width, int height) {
		super(width, height);
		wholemap=new ObjTile[width][height];
	}

	@Override
	public int[][] display(int cameraX, int cameraY, int size) {
		int[][] output=new int[size][size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				int newX=cameraX+i;
				int newY=cameraY+j;
				if(outOfBound(newX,newY)) {
					output[i][j]=nullValue;
				}
				else if(wholemap[newX][newY]==null) {
					output[i][j]=nullValue;
				}
				else
					output[i][j]=wholemap[newX][newY].index;
			}
		}
		return output;
	}
	public void set(ObjTile obj) {
		wholemap[obj.getX()][obj.getY()]=obj;
	}
	public void move(int x,int y,int newX,int newY) {
		ObjTile temp=wholemap[x][y];
		if(temp!=null)
		{
			wholemap[newX][newY]=temp;
			wholemap[x][y]=null;
		}
		
	}
	public boolean outOfBound(int x,int y) {
		if(x<0||y<0||x>=width||y>=height)
			return true;
		else
			return false;
	}
	public boolean canPass(int x,int y) {
		return wholemap[x][y]==null;
	}
	
}
