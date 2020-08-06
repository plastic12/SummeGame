package main;

import org.json.simple.JSONObject;

public class DialogObj implements ObjTile,Event{
	public String text;
	private int x;
	private int y;
	private int index;
	
	
	public DialogObj(String text,int x,int y,int index) {
		this.text=text;
		this.x=x;
		this.y=y;
		this.index=index;
	}
	public DialogObj(JSONObject input) {
		text=(String)input.get("text");
		x=((Long)input.get("x")).intValue();
		y=((Long)input.get("y")).intValue();
		index=((Long)input.get("index")).intValue();
	}

	@Override
	public void event(MainChar c) {
		Main.mapPane.setText(text);
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
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject output=new JSONObject();
		output.put("text", text);
		output.put("x", x);
		output.put("y",y);
		output.put("index",index);
		output.put("classType", ObjTile.DIALOG);
		return output;
	}

}
