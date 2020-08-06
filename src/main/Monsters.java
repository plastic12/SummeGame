package main;

import org.json.simple.JSONObject;

public class Monsters extends Party implements Event{

	private Monsters(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public Monsters(JSONObject input) {
		super(input);
	}
	public static Monsters getCentaur(int x, int y) {
		Monsters output=new Monsters(x,y);
		output.characters[0]=Monster.characterModel1();
		output.characters[1]=Monster.characterModel1();
		output.characters[2]=Monster.characterModel1();
		output.characters[3]=Monster.characterModel1();
		output.characters[4]=Monster.characterModel1();
		output.setFormation(0,0,0);
		output.setFormation(0,1,1);
		output.setFormation(0,2,2);
		output.setFormation(1,0,3);
		output.setFormation(1,1,4);
		return output;
	}
	@Override
	public void event(MainChar c) {
		Main.battle(this);
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject output=super.toJSON();
		output.put("classType",ObjTile.MONSTERS);
		return output;
	}
	
}
