package main;

import java.util.TreeSet;

import org.json.simple.JSONObject;

public class Map {
	private BGLayer bgLayer;
	private ObjLayer objLayer;
	private int width;
	private int height;
	public static final int nullValue=-1;
	private int spawnX;
	private int spawnY;
	private TreeSet<ObjTile> entities;
	
	
	public Map(int width,int height) {
		this.width=width;
		this.height=height;
		bgLayer=new BGLayer(width,height,60);
		objLayer=new ObjLayer(width,height);
		entities=new TreeSet<ObjTile>();
	}
	public Map(JSONObject input) {
		width=((Long)input.get("width")).intValue();
		height=((Long)input.get("height")).intValue();
		spawnX=((Long)input.get("spawnX")).intValue();
		spawnY=((Long)input.get("spawnY")).intValue();
		bgLayer=new BGLayer(input);
		objLayer=new ObjLayer(input);
		entities=new TreeSet<ObjTile>();
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
	public int getSpawnX() {return spawnX;}
	public int getSpawnY() {return spawnY;}
	public void addEntity(ObjTile e) {
		int x=e.getX();
		int y=e.getY();
		if(objLayer.get(x, y)==Layer.nullValue)
		{
			entities.add(e);
			objLayer.set(e);
		}
		else
			System.out.println("object already exist in the tile.");
	}
	//dont call this call the character version
	public void move(ObjTile e,int x,int y,int newX,int newY) {
		if(e==entities.ceiling(e)) {
			entities.remove(e);
			entities.add(e);
			objLayer.move(x, y, newX, newY);
		}
		else
			return;
	}
	public ObjLayer getObjectLayer() {return objLayer;}
	public boolean canMove(int x,int y) {
		if(objLayer.outOfBound(x, y))
			return false;
		else
			return bgLayer.canPass(x, y)&&objLayer.canPass(x, y);
	}
}
