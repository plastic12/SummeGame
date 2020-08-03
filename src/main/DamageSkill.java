package main;

import java.util.List;

public class DamageSkill extends Skill{
	private int damage;
	private int rangeType;
	public DamageSkill(String name,int damage,int indicator,int image) {
		super(name,image);
		this.damage=damage;
		rangeType=indicator;
	}


	@Override
	public List<Position> getSelector(Party p) {
		return Skill.standardSelector(rangeType, p);
	}


	@Override
	public void action(Entity entity, Party p, int x, int y) {
		Entity target=p.getFormation(x, y);
		target.damage(damage);
	}

}
