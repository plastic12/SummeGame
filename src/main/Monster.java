package main;

import org.json.simple.JSONObject;

import javafx.beans.property.SimpleIntegerProperty;

public class Monster extends Entity{
	
	private int def;
	protected Item drop;
	private Monster() {
		HP=new SimpleIntegerProperty();
		MP=new SimpleIntegerProperty();
		MaxHP=new SimpleIntegerProperty();
		MaxMP=new SimpleIntegerProperty();
	}
	protected Monster(JSONObject input) {
		super(input);
		def=((Long)input.get("def")).intValue();
		
	}
	@Override
	public void damage(int d) {
		int damage=(d>def)? d-def:0;
		HP.set((HP.get()>damage)? HP.get()-damage:0);
	}
	public static Monster characterModel1() {
		Monster m=new Monster();
		m.name="Centaur";
		m.level=1;
		m.HP.set(10);
		m.MP.set(10);
		m.MaxHP.set(m.getHP());
		m.MaxMP.set(m.getMP());
		m.agi=10;
		m.str=10;
		m.Intell=5;
		m.luck=15;
		m.image=16;
		m.def=1;
		return m;
	}
	public static Monster characterModel2() {
		Monster m=new Monster();
		m.name="Slark";
		m.level=1;
		m.HP.set(10);
		m.MP.set(10);
		m.MaxHP.set(m.getHP());
		m.MaxMP.set(m.getMP());
		m.agi=10;
		m.str=10;
		m.Intell=5;
		m.luck=15;
		m.image=17;
		m.def=1;
		return m;
	}
	public static Monster characterModel3() {
		Monster m=new Monster();
		m.name="ghost";
		m.level=1;
		m.HP.set(10);
		m.MP.set(10);
		m.MaxHP.set(m.getHP());
		m.MaxMP.set(m.getMP());
		m.agi=10;
		m.str=10;
		m.Intell=5;
		m.luck=15;
		m.image=18;
		m.def=1;
		return m;
	}
	public static Monster characterModel4() {
		Monster m=new Monster();
		m.name="Gargoyle";
		m.level=1;
		m.HP.set(10);
		m.MP.set(10);
		m.MaxHP.set(m.getHP());
		m.MaxMP.set(m.getMP());
		m.agi=10;
		m.str=10;
		m.Intell=5;
		m.luck=15;
		m.image=19;
		m.def=1;
		return m;
	}
	public static Monster characterModel5() {
		Monster m=new Monster();
		m.name="Imp";
		m.level=1;
		m.HP.set(10);
		m.MP.set(10);
		m.MaxHP.set(m.getHP());
		m.MaxMP.set(m.getMP());
		m.agi=10;
		m.str=10;
		m.Intell=5;
		m.luck=15;
		m.image=20;
		m.def=1;
		return m;
	}
	public JSONObject toJSON() {
		JSONObject output=super.toJSON();
		output.put("entityType",Entity.MONSTER);
		output.put("def",def);
		return output;
	}
}
