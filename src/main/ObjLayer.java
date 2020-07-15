package main;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ObjLayer extends Layer{
	public int[][] wholemap;
	
	public ObjLayer(int width, int height) {
		super(width, height);
		wholemap=new int[width][height];
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				wholemap[i][j]=nullValue;
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
					output[i][j]=wholemap[newX][newY];
			}
		}
		return output;
	}
	public void set(ObjTile obj) {
		wholemap[obj.getX()][obj.getY()]=obj.index;
	}
	public void move(int x,int y,int newX,int newY) {
		int temp=wholemap[x][y];
		if(temp!=nullValue)
		{
			wholemap[newX][newY]=temp;
			wholemap[x][y]=nullValue;
		}
	}
	public ObjLayer(JSONObject input) {
		super(((Long)input.get("width")).intValue(),((Long)input.get("height")).intValue());
		wholemap=new int[width][height];
		JSONArray data=(JSONArray)input.get("objLayer");
		@SuppressWarnings("unchecked")
		Iterator<Long>iter=data.iterator();
		for(int j=0;j<height;j++) {
			for(int i=0;i<width;i++) {
				if(iter.hasNext())
					wholemap[i][j]=iter.next().intValue();
				else
					wholemap[i][j]=0;
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
		return wholemap[x][y];
	}
	public boolean canPass(int x,int y) {
		return wholemap[x][y]==nullValue;
	}
	
}
