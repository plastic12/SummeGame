package main;

import org.json.simple.JSONObject;

public abstract class Item {
	protected String name;
	protected int index;
	
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
	public UpdateImView getImage() {
		return new UpdateImView(32,32,index,Library.textures.get(3));
	}
	public Item(JSONObject input) {
		name=(String)input.get("name");
		index=((Long)input.get("index")).intValue();
	}
	public Item() {}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject output=new JSONObject();
		output.put("name",name);
		output.put("index",index);
		return output;
	}
}
