package main;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ObjLayer extends Layer{
	public int[][] d;
	
	public ObjLayer(int width, int height) {
		super(width, height);
		d=new int[width][height];
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				d[i][j]=nullValue;
			}
		}
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
				else
					output[i][j]=d[newX][newY];
			}
		}
		return output;
	}
	public void set(ObjTile obj) {
		d[obj.getX()][obj.getY()]=obj.getIndex();
	}
	public void remove(int x,int y) {d[x][y]=-1;}
	public void move(int x,int y,int newX,int newY) {
		int temp=d[x][y];
		if(temp!=nullValue)
		{
			d[newX][newY]=temp;
			d[x][y]=nullValue;
		}
	}
	public ObjLayer(JSONObject input) {
		super(((Long)input.get("width")).intValue(),((Long)input.get("height")).intValue());
		d=new int[width][height];
		JSONArray data=(JSONArray)input.get("LayerObj");
		@SuppressWarnings("unchecked")
		Iterator<Long>iter=data.iterator();
		for(int j=0;j<height;j++) {
			for(int i=0;i<width;i++) {
				if(iter.hasNext())
					d[i][j]=iter.next().intValue();
				else
					d[i][j]=0;
			}
		}
		
	}
	public boolean outOfBound(int x,int y) {
		if(x<0||y<0||x>=width||y>=height)
			return true;
		else
			return false;
	}
	public int get(int x,int y) {
		return d[x][y];
	}
	public boolean canPass(int x,int y) {
		return d[x][y]==nullValue;
	}
	
}
