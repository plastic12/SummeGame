package main;

import org.json.simple.JSONObject;

public interface ObjTile {
	public static final int DIALOG=0;
	public static final int MONSTERS=1;
	public static final int MC=2;
	public static final int TELEPORT=3;

	public int getX();
	public int getY();

	public int getIndex();

	public JSONObject toJSON();

	public default Position getPosition() {
		return new Position(getX(),getY());
	}
	public static ObjTile getObjTile(JSONObject input) {
		int classType=((Long)input.get("classType")).intValue();
		switch(classType) {
		case DIALOG:
			return new DialogObj(input);
		case MONSTERS:
			return new Monsters(input);
		case TELEPORT:
			return new Teleport(input);
		default:
			return null;
		}
	}





}
