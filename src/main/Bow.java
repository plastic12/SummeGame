package main;

import java.util.List;

import org.json.simple.JSONObject;

public class Bow extends Weapon{
	
	public Bow(JSONObject input) {
		super(input);
		skill=new Skill(name,index) {
			@Override
			public void action(Entity entity, Party p, int x, int y) {
				Entity target=p.getFormation(x, y);
				target.damage(atk);
			}

			@Override
			public List<Position> getSelector(Party p) {
				return Skill.standardSelector(Skill.RANGE, p);
			}
			
		};
	}
	
	public Bow() {
		skill=new Skill(name,index) {
			@Override
			public void action(Entity entity, Party p, int x, int y) {
				Entity target=p.getFormation(x, y);
				target.damage(atk);
			}

			@Override
			public List<Position> getSelector(Party p) {
				return Skill.standardSelector(Skill.RANGE, p);
			}
			
		};
	}

	@Override
	public Skill getSkill() {
		return skill;
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject output=super.toJSON();
		output.put("Type",Weapon.BOW);
		return output;
	}
}
