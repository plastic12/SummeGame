package main;

import org.json.simple.JSONObject;

public abstract class Weapon extends Item{
	protected int atk;
	public Character c;
	protected Skill skill;
	public static final int BOW=0;
	
	public Weapon(JSONObject input) {
		super(input);
		atk=((Long)input.get("atk")).intValue();
	}
	public Weapon() {}
	
	public static Weapon getBow() {
		Weapon w=new Bow();
		w.name="Bow 1";
		w.atk=10;
		w.index=0;
		return w;
	}
	public static Weapon getWeapon(JSONObject input) {
		int type=((Long)input.get("type")).intValue();
		switch(type) {
		case BOW:
			return new Bow();
		default:
			return null;
		}
	}
	
	
	
	public Skill getSkill() {
		return skill;
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject output=super.toJSON();
		output.put("atk",atk);
		return output;
	}
	
	
}
