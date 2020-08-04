package main;

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

}
