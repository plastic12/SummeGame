package main;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BGLayer extends Layer{
	private int[][] wholemap;
	
	private static ArrayList<Tile> tiles=new ArrayList<Tile>();
	
	
	public BGLayer(int width, int height) {
		this(width,height,0);
	}
	public BGLayer(int width,int height,int defaultValue) {
		super(width, height);
		wholemap=new int[width][height];
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				wholemap[i][j]=defaultValue;
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static void loadData(JSONObject input) {
		JSONArray tileset=(JSONArray)input.get("tileset");
		for(Object e:tileset) {
			tiles.add(new Tile((JSONObject)e));
		}
		
	}
	public BGLayer(JSONObject input) {
		super(((Long)input.get("width")).intValue(),((Long)input.get("height")).intValue());
		wholemap=new int[width][height];
		JSONArray data=(JSONArray)input.get("data");
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

	@Override
	public int[][] display(int cameraX, int cameraY, int size) 	{
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
	public boolean canPass(int x,int y) {
		return tiles.get(wholemap[x][y]).canPass;
	}
}
