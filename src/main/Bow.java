package main;

import java.util.List;

public class Bow extends Weapon{
	
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
}
