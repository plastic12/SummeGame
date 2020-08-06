package main;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Map {
	private String name;
	private BGLayer bgLayer;
	private ObjLayer objLayer;
	private int width;
	private int height;
	public static final int nullValue=-1;
	private TreeMap<String,Position> locations;
	private TreeMap<Position,ObjTile> entities;


	public Map(int width,int height) {
		this.width=width;
		this.height=height;
		bgLayer=new BGLayer(width,height,60);
		objLayer=new ObjLayer(width,height);
		entities=new TreeMap<Position,ObjTile>();
	}
	@SuppressWarnings("unchecked")
	public Map(JSONObject input) {
		locations=new TreeMap<String,Position>();
		entities=new TreeMap<Position,ObjTile>();
		name=(String)input.get("name");
		width=((Long)input.get("width")).intValue();
		height=((Long)input.get("height")).intValue();
		JSONObject locationsData=(JSONObject) input.get("locations");
		Set<String> set=locationsData.keySet();
		for(String s:set) {
			locations.put(s,new Position((JSONArray)locationsData.get(s)) );
		}
		bgLayer=new BGLayer(input);
		objLayer=new ObjLayer(input);
		entities=new TreeMap<Position,ObjTile>();

		JSONArray objData=(JSONArray)input.get("objects");
		Iterator<JSONObject> iter=objData.iterator();
		while(iter.hasNext()) {
			addEntity(ObjTile.getObjTile(iter.next()));
		}


	}
	public Position getLocation(String name) {
		return locations.get(name);
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
	public String getName() {return name;}
	public void addEntity(ObjTile e) {
		int x=e.getX();
		int y=e.getY();
		if(objLayer.get(x, y)==Layer.nullValue)
		{
			entities.put(new Position(e.getX(),e.getY()), e);
			objLayer.set(e);
		}
		else
			System.out.println("object already exist in the tile.");
	}
	public void removeEntity(ObjTile e) {
		int x=e.getX();
		int y=e.getY();
		entities.remove(new Position(x,y));
		objLayer.remove(x, y);
	}
	//dont call this call the character version
	public void move(Movable e,int x,int y,int newX,int newY) {
		Position key=e.getPosition();
		if(e==entities.get(key)) {
			entities.remove(key);
			objLayer.move(x, y, newX, newY);
			if(x!=newX)
				e.setX(newX);
			if(y!=newY)
				e.setY(newY);
			entities.put(e.getPosition(), e);

		}
		else
			return;
	}
	public ObjTile getEntity(int x,int y) {
		return entities.get(new Position(x,y));
	}
	public ObjLayer getObjectLayer() {return objLayer;}
	public boolean canMove(int x,int y) {
		if(objLayer.outOfBound(x, y))
			return false;
		else
			return bgLayer.canPass(x, y)&&objLayer.canPass(x, y);
	}
}
