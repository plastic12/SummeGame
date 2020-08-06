package main;

import org.json.simple.JSONObject;

public class Teleport implements ObjTile,Event{
	private int x;
	private int y;
	private int mapIndex;
	private String location;
	
	
	public Teleport(int x,int y,int mapIndex,String location) {
		this.x=x;
		this.y=y;
		this.mapIndex=mapIndex;
		this.location=location;
	}
	public Teleport(JSONObject input) {
		x=((Long)input.get("x")).intValue();
		y=((Long)input.get("y")).intValue();
		mapIndex=((Long)input.get("mapIndex")).intValue();
		location=(String)input.get("location");
		
		
	}
	

	@Override
	public void event(MainChar c) {
		Main.changeMap(mapIndex, location);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getIndex() {
		return 25;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject output=new JSONObject();
		output.put("x",x);
		output.put("y",y);
		output.put("mapIndex",mapIndex);
		output.put("location",location);
		output.put("classType",ObjTile.TELEPORT);
		return output;
	}

}
