package main;

public class Item {
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
}
